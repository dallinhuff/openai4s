package com.dallinhuff.openai4s.entities.chat

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class ChatChunk(
    id: String,
    choices: List[ChatChunkChoice],
    created: Int,
    model: String,
    system_fingerprint: Option[String],
    `object`: String // TODO
)

object ChatChunk:
  given Codec[ChatChunk] = deriveCodec[ChatChunk]
