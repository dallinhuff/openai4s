package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.generic.semiauto.*

case class Usage(completion_tokens: Int, prompt_tokens: Int, total_tokens: Int)

object Usage:
  given Codec[Usage] = deriveCodec[Usage]
