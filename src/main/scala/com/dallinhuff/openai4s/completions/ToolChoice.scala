package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum ToolChoice:
  case None
  case Auto
  case Function(name: String)

object ToolChoice:
  given Codec[ToolChoice] with
    override def apply(a: ToolChoice): Json =
      a match
        case ToolChoice.None => Json.fromString("none")
        case ToolChoice.Auto => Json.fromString("auto")
        case ToolChoice.Function(name) => Json.obj(
          "type" -> Json.fromString("function"),
          "name" -> name.asJson
        )
    override def apply(c: HCursor): Result[ToolChoice] =
      c.as[String]
        .map:
          case "none" => ToolChoice.None
          case "auto" => ToolChoice.Auto
        .orElse:
          c.downField("type").as[String].flatMap:
            case "function" =>
              c.downField("name").as[String].map(ToolChoice.Function(_))
            case _ => Left(DecodingFailure("bad type", c.history))
          

