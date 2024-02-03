package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*

case class ChatChoice(
    finish_reason: String,
    index: Int,
    message: ChatMessage.Assistant,
    logprobs: Option[LogProbs] = None
)

object ChatChoice:
  given Codec[ChatChoice] = deriveCodec[ChatChoice]
