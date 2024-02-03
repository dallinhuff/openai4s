package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum ChatChunkToolCall(index: Int, id: String):
  case Function(
      index: Int,
      id: String,
      name: String,
      arguments: String
  ) extends ChatChunkToolCall(index, id)

object ChatChunkToolCall:
  given Codec[ChatChunkToolCall] with
    override def apply(a: ChatChunkToolCall): Json =
      a match
        case ChatChunkToolCall.Function(index, id, name, arguments) =>
          Json.obj(
            "index" -> index.asJson,
            "id"    -> id.asJson,
            "type"  -> "function".asJson,
            "function" -> Json.obj(
              "name"      -> name.asJson,
              "arguments" -> arguments.asJson
            )
          )
    override def apply(c: HCursor): Result[ChatChunkToolCall] =
      for
        ind <- c.downField("index").as[Int]
        id  <- c.downField("id").as[String]
        _   <- c.downField("type").as[String].ensuring(_ == Right("function"))
        nm  <- c.downField("function").downField("name").as[String]
        arg <- c.downField("function").downField("arguments").as[String]
      yield ChatChunkToolCall.Function(ind, id, nm, arg)
