package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum Message:
  case SystemMessage(content: String, name: Option[String] = None)
  case UserMessage(content: List[UserContent], name: Option[String] = None)
  case AssistantMessage(
    content: Option[String],
    name: Option[String],
    tool_calls: Option[List[ToolCall]] = None
  )
  case ToolMessage(content: String, tool_call_id: String)

object Message:
  def UserMessage(content: String, name: Option[String] = None): Message.UserMessage =
    Message.UserMessage(List(UserContent.Text(content)), name)

  given sysCodec: Codec[Message.SystemMessage] = new Codec[Message.SystemMessage]:
    override def apply(a: Message.SystemMessage): Json =
      deriveEncoder[Message.SystemMessage](a)
        .asJson
        .dropNullValues
        .deepMerge(Json.obj("role" -> "system".asJson))
    override def apply(c: HCursor): Result[Message.SystemMessage] =
      deriveDecoder[Message.SystemMessage](c)

  given toolCodec: Codec[Message.ToolMessage] = new Codec[Message.ToolMessage]:
    override def apply(a: Message.ToolMessage): Json =
      deriveEncoder[Message.ToolMessage](a)
        .asJson
        .dropNullValues
        .deepMerge(Json.obj("role" -> "tool".asJson))
    override def apply(c: HCursor): Result[Message.ToolMessage] =
      deriveDecoder[Message.ToolMessage](c)

  given usrCodec: Codec[Message.UserMessage] = new Codec[Message.UserMessage]:
    override def apply(a: Message.UserMessage): Json =
      deriveEncoder[Message.UserMessage](a)
        .asJson
        .dropNullValues
        .deepMerge(Json.obj("role" -> "user".asJson))
    override def apply(c: HCursor): Result[Message.UserMessage] =
      deriveDecoder[Message.UserMessage](c)

  given asstCodec: Codec[Message.AssistantMessage] = new Codec[Message.AssistantMessage]:
    override def apply(a: Message.AssistantMessage): Json =
      deriveEncoder[Message.AssistantMessage](a)
        .asJson
        .dropNullValues
        .deepMerge(Json.obj("role" -> "assistant".asJson))
    override def apply(c: HCursor): Result[Message.AssistantMessage] =
      deriveDecoder[Message.AssistantMessage](c)

  given Codec[Message] with
    override def apply(a: Message): Json =
      a match
        case s @ Message.SystemMessage(_, _) => s.asJson
        case u @ Message.UserMessage(_, _) => u.asJson
        case a @ Message.AssistantMessage(_, _, _) => a.asJson
        case t @ Message.ToolMessage(_, _) => t.asJson

    override def apply(c: HCursor): Result[Message] =
      c.downField("role").as[String].flatMap:
        case "system" => sysCodec(c)
        case "user" => usrCodec(c)
        case "assistant" => asstCodec(c)
        case "tool" => toolCodec(c)
        case _ => Left(DecodingFailure("bad role", c.history))
end Message
