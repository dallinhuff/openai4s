package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*

case class ChatChunkDelta(
    content: Option[String],
    tool_calls: List[ChatChunkToolCall],
    role: String
)

object ChatChunkDelta:
  given Codec[ChatChunkDelta] = deriveCodec[ChatChunkDelta]
