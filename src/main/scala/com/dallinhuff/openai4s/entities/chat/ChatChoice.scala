package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*

case class ChatChoice(
    finish_reason: String,
    index: Int,
    message: ChatMessage,
    logprobs: Option[LogProbs] = None
)

object ChatChoice:
  given Decoder[ChatChoice] = deriveDecoder[ChatChoice]
  given Encoder[ChatChoice] = deriveEncoder[ChatChoice]
    .mapJson(_.dropNullValues)
