package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.generic.semiauto.*

case class ChatCompletion(
  id: String,
  choices: List[Choice],
  created: Int,
  model: String,
  system_fingerprint: Option[String],
  `object`: String,
  usage: Usage
)

object ChatCompletion:
  given Codec[ChatCompletion] = deriveCodec[ChatCompletion]

