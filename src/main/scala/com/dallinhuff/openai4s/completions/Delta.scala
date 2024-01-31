package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.generic.semiauto.*

case class Delta(
  content: Option[String],
  tool_calls: List[ChunkToolCall],
  role: String
)

object Delta:
  given Codec[Delta] = deriveCodec[Delta]