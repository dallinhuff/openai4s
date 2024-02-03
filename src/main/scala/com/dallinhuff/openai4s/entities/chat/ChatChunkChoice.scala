package com.dallinhuff.openai4s.entities.chat

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class ChatChunkChoice(
    delta: ChatChunkDelta,
    logprobs: Option[LogProbs],
    finish_reason: Option[String],
    index: Integer
)

object ChatChunkChoice:
  given Codec[ChatChunkChoice] = deriveCodec[ChatChunkChoice]
