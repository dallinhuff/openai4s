package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum Tool:
  case Function(name: String, description: Option[String] = None, parameters: ToolParams)

type ToolParams = Map[String, String] // TODO

object Tool:
  private given fnCodec: Codec[Tool.Function] =
    deriveCodec[Tool.Function]
  
  given Codec[Tool] with
    override def apply(a: Tool): Json =
      a match
        case f @ Tool.Function(_, _, _) => f.asJson
        
    override def apply(c: HCursor): Result[Tool] =
      c.downField("type").as[String].flatMap:
        case "function" => fnCodec(c)
        case _ => Left(DecodingFailure("bad type", c.history))