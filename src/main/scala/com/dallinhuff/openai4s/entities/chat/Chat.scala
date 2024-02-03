package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.syntax.*

case class Chat(
    id: String,
    choices: List[ChatChoice],
    created: Int,
    model: String,
    system_fingerprint: Option[String],
    usage: Usage
)

object Chat:
  given Decoder[Chat] = deriveDecoder[Chat]
  given Encoder[Chat] = deriveEncoder[Chat]
    .mapJson(_.deepMerge(Json.obj("object" -> "chat.completion".asJson)))
    .mapJson(_.dropNullValues)
