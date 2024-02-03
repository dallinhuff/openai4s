package com.dallinhuff.openai4s.entities.threads

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ThreadMessage(
    id: String,
    created_at: Int,
    thread_id: String,
    role: String,
    content: List[ThreadMessageContent],
    assistant_id: Option[String],
    run_id: Option[String],
    file_ids: List[String],
    metadata: Map[String, String]
)

object ThreadMessage:
  given Decoder[ThreadMessage] = deriveDecoder[ThreadMessage]
  given Encoder[ThreadMessage] = deriveEncoder[ThreadMessage]
    .mapJson(
      _.deepMerge(
        Json.obj("object" -> Json.fromString("thread.message"))
      ).dropNullValues
    )
