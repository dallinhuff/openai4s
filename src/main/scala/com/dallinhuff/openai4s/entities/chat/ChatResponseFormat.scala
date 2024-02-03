package com.dallinhuff.openai4s.entities.chat

import io.circe.*

enum ChatResponseFormat:
  case Text
  case JsonObject

object ChatResponseFormat:
  given Codec[ChatResponseFormat] with
    override def apply(a: ChatResponseFormat): Json =
      a match
        case ChatResponseFormat.Text =>
          Json.obj("type" -> Json.fromString("text"))
        case ChatResponseFormat.JsonObject =>
          Json.obj("type" -> Json.fromString("json_object"))

    override def apply(c: HCursor): Decoder.Result[ChatResponseFormat] =
      c.downField("type")
        .as[String]
        .flatMap:
          case "text"        => Right(ChatResponseFormat.Text)
          case "json_object" => Right(ChatResponseFormat.JsonObject)
          case _ => Left(DecodingFailure("bad response_format type", c.history))
