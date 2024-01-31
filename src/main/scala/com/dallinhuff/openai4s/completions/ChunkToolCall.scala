package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum ChunkToolCall(index: Int, id: String):
  case Function(index: Int, id: String, name: String, arguments: String)
    extends ChunkToolCall(index, id)

object ChunkToolCall:
  given Codec[ChunkToolCall] with
    override def apply(a: ChunkToolCall): Json =
      a match
        case ChunkToolCall.Function(index, id, name, arguments) =>
          Json.obj(
            "index" -> index.asJson,
            "id" -> id.asJson,
            "type" -> "function".asJson,
            "function" -> Json.obj(
              "name" -> name.asJson,
              "arguments" -> arguments.asJson
            )
          )
    override def apply(c: HCursor): Result[ChunkToolCall] =
      for
        ind <- c.downField("index").as[Int]
        id  <- c.downField("id").as[String]
        _   <- c.downField("type").as[String].ensuring(_ == Right("function"))
        nm  <- c.downField("function").downField("name").as[String]
        arg <- c.downField("function").downField("arguments").as[String]
      yield ChunkToolCall.Function(ind, id, nm, arg)
