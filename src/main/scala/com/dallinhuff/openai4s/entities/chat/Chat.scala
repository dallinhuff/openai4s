package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*

case class Chat(
    id: String,
    choices: List[ChatChoice],
    created: Int,
    model: String,
    system_fingerprint: Option[String],
    `object`: String, // TODO
    usage: Usage
)

object Chat:
  given Codec[Chat] = deriveCodec[Chat]
