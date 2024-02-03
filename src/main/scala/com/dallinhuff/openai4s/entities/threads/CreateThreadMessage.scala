package com.dallinhuff.openai4s.entities.threads

import io.circe.Decoder.Result
import io.circe.generic.semiauto.deriveEncoder
import io.circe.{Codec, DecodingFailure, Encoder, HCursor, Json}
import io.circe.syntax.*

enum CreateThreadMessage:
  case User(
      content: String,
      file_ids: Option[List[String]] = None,
      metadata: Option[Map[String, String]]
  )

object CreateThreadMessage:
  private given Encoder[CreateThreadMessage.User] =
    deriveEncoder[CreateThreadMessage.User]

  given Codec[CreateThreadMessage] with
    override def apply(a: CreateThreadMessage): Json =
      a match
        case usrMsg: CreateThreadMessage.User =>
          usrMsg.asJson
            .deepMerge(Json.obj("role" -> "user".asJson))
            .dropNullValues

    override def apply(c: HCursor): Result[CreateThreadMessage] =
      for
        role     <- c.downField("role").as[String]
        content  <- c.downField("content").as[String]
        file_ids <- c.downField("file_ids").as[Option[List[String]]]
        metadata <- c.downField("metadata").as[Option[Map[String, String]]]
        result <- role match
          case "user" =>
            Right(CreateThreadMessage.User(content, file_ids, metadata))
          case _ => Left(DecodingFailure(s"bad role: $role", c.history))
      yield result
