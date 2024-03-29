package com.dallinhuff.openai4s.entities.assistants

import com.dallinhuff.openai4s.entities.chat.Tool
import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.*

case class Assistant(
    id: String,
    created_at: Int,
    name: Option[String],
    description: Option[String],
    model: String,
    instructions: Option[String],
    tools: List[Tool],
    file_ids: List[String],
    metadata: Map[String, String]
)

object Assistant:
  given Decoder[Assistant] = deriveDecoder[Assistant]

  given Encoder[Assistant] =
    deriveEncoder[Assistant]
      .mapJson(
        _.deepMerge(Json.obj("object" -> "assistant".asJson)).dropNullValues
      )
