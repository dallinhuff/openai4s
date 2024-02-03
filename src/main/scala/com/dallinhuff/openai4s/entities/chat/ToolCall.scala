package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum ToolCall:
  case Function(id: String, arguments: String)

object ToolCall:
  private given fCodec: Codec[ToolCall.Function] =
    deriveCodec[ToolCall.Function]

  given Codec[ToolCall] with
    override def apply(a: ToolCall): Json =
      a match
        case f: ToolCall.Function =>
          Json.obj(
            "type"     -> "function".asJson,
            "function" -> f.asJson
          )
    override def apply(c: HCursor): Result[ToolCall] =
      for
        _ <- c.downField("type").as[String]
        f <- c.downField("function").as[ToolCall.Function]
      yield f
