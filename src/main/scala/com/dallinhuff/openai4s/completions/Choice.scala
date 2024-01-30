package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.generic.semiauto.*

case class Choice(
  finish_reason: String, 
  index: Int,
  message: Message.AssistantMessage,
  logprobs: Option[LogProbs] = None
)

object Choice:
  given Codec[Choice] = deriveCodec[Choice]