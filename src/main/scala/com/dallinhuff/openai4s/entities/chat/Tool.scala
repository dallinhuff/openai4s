package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum Tool:
  case CodeInterpreter
  case Retrieval
  case Function(
      name: String,
      description: Option[String] = None,
      parameters: Option[ToolParams] = None
  )

type ToolParams = Map[String, String] // TODO

object Tool:
  given cEncoder: Encoder[Tool.CodeInterpreter.type] with
    override def apply(a: Tool.CodeInterpreter.type): Json =
      Json.obj("type" -> "code_interpreter".asJson)

  given rEncoder: Encoder[Tool.Retrieval.type] with
    override def apply(a: Tool.Retrieval.type): Json =
      Json.obj("type" -> "retrieval".asJson)

  given fnCodec: Codec[Tool.Function] with
    override def apply(a: Tool.Function): Json =
      Json
        .obj(
          "type"        -> "function".asJson,
          "name"        -> a.name.asJson,
          "description" -> a.description.asJson,
          "parameters"  -> a.parameters.asJson
        )
        .dropNullValues

    override def apply(c: HCursor): Result[Tool.Function] =
      for
        _ <- c.downField("type").as[String].ensuring(_ == Right("function"))
        n <- c.downField("name").as[String]
        d <- c.downField("description").as[Option[String]]
        p <- c.downField("parameters").as[Option[ToolParams]]
      yield Tool.Function(n, d, p)

  given Codec[Tool] with
    override def apply(a: Tool): Json =
      a match
        case c: Tool.CodeInterpreter.type => cEncoder(c)
        case r: Tool.Retrieval.type       => rEncoder(r)
        case f: Tool.Function             => fnCodec(f)

    override def apply(c: HCursor): Result[Tool] =
      c.downField("type")
        .as[String]
        .flatMap:
          case "code_interpreter" => Right(Tool.CodeInterpreter)
          case "retrieval"        => Right(Tool.Retrieval)
          case "function"         => fnCodec(c)
          case _ => Left(DecodingFailure("bad type", c.history))
