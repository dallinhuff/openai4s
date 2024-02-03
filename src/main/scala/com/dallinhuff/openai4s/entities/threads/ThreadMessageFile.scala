package com.dallinhuff.openai4s.entities.threads

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ThreadMessageFile(
    id: String,
    created_at: Int,
    message_id: String
)

object ThreadMessageFile:
  given Decoder[ThreadMessageFile] = deriveDecoder[ThreadMessageFile]
  given Encoder[ThreadMessageFile] = deriveEncoder[ThreadMessageFile]
    .mapJson(
      _.deepMerge(Json.obj("object" -> Json.fromString("thread.message.file")))
    )
