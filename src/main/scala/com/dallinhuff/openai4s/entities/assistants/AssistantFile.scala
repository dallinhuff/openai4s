package com.dallinhuff.openai4s.entities.assistants

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.*

case class AssistantFile(id: String, created_at: Integer, assistant_id: String)

object AssistantFile:
  given Decoder[AssistantFile] = deriveDecoder[AssistantFile]

  given Encoder[AssistantFile] = deriveEncoder[AssistantFile]
    .mapJson(_.deepMerge(Json.obj("object" -> "assistant.file".asJson)))
    .mapJson(_.dropNullValues)
