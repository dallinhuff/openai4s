package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum ChatMessage:
  case System(content: String, name: Option[String] = None)
  case User(content: List[UserContent], name: Option[String] = None)
  case Assistant(
      content: Option[String] = None,
      name: Option[String] = None,
      tool_calls: Option[List[ToolCall]] = None
  )
  case Tool(content: String, tool_call_id: String)

object ChatMessage:
  def User(content: String, name: Option[String] = None): ChatMessage.User =
    ChatMessage.User(List(UserContent.Text(content)), name)

  given sysCodec: Codec[ChatMessage.System] = new Codec[ChatMessage.System]:
    override def apply(a: ChatMessage.System): Json =
      deriveEncoder[ChatMessage.System](a).asJson.dropNullValues
        .deepMerge(Json.obj("role" -> "system".asJson))
    override def apply(c: HCursor): Result[ChatMessage.System] =
      deriveDecoder[ChatMessage.System](c)

  given toolCodec: Codec[ChatMessage.Tool] = new Codec[ChatMessage.Tool]:
    override def apply(a: ChatMessage.Tool): Json =
      deriveEncoder[ChatMessage.Tool](a).asJson.dropNullValues
        .deepMerge(Json.obj("role" -> "tool".asJson))
    override def apply(c: HCursor): Result[ChatMessage.Tool] =
      deriveDecoder[ChatMessage.Tool](c)

  given usrCodec: Codec[ChatMessage.User] = new Codec[ChatMessage.User]:
    override def apply(a: ChatMessage.User): Json =
      deriveEncoder[ChatMessage.User](a).asJson.dropNullValues
        .deepMerge(Json.obj("role" -> "user".asJson))
    override def apply(c: HCursor): Result[ChatMessage.User] =
      deriveDecoder[ChatMessage.User](c)

  given asstCodec: Codec[ChatMessage.Assistant] =
    new Codec[ChatMessage.Assistant]:
      override def apply(a: ChatMessage.Assistant): Json =
        deriveEncoder[ChatMessage.Assistant](a).asJson.dropNullValues
          .deepMerge(Json.obj("role" -> "assistant".asJson))
      override def apply(c: HCursor): Result[ChatMessage.Assistant] =
        deriveDecoder[ChatMessage.Assistant](c)

  given Codec[ChatMessage] with
    override def apply(a: ChatMessage): Json =
      a match
        case s: ChatMessage.System    => s.asJson
        case u: ChatMessage.User      => u.asJson
        case a: ChatMessage.Assistant => a.asJson
        case t: ChatMessage.Tool      => t.asJson

    override def apply(c: HCursor): Result[ChatMessage] =
      c.downField("role")
        .as[String]
        .flatMap:
          case "system"    => sysCodec(c)
          case "user"      => usrCodec(c)
          case "assistant" => asstCodec(c)
          case "tool"      => toolCodec(c)
          case _           => Left(DecodingFailure("bad role", c.history))
end ChatMessage
