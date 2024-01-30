package com.dallinhuff.openai4s.completions

import io.circe.*

enum ResponseFormat:
  case Text
  case JsonObject

object ResponseFormat:
  given Codec[ResponseFormat] with
    override def apply(a: ResponseFormat): Json =
      a match
        case ResponseFormat.Text =>
          Json.obj("type" -> Json.fromString("text"))
        case ResponseFormat.JsonObject =>
          Json.obj("type" -> Json.fromString("json_object"))
    
    override def apply(c: HCursor): Decoder.Result[ResponseFormat] =
      c.downField("type").as[String].flatMap:
        case "text" => Right(ResponseFormat.Text)
        case "json_object" => Right(ResponseFormat.JsonObject)
        case _ => Left(DecodingFailure("bad response_format type", c.history))
