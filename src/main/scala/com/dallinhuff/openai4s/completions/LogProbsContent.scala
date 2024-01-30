package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.generic.semiauto.*

case class LogProbsContent(
  token: String,
  logprob: Double,
  bytes: Option[Array[Byte]],
  top_logprobs: Option[List[LogProbsContent]] = None
)

object LogProbsContent:
  given Codec[LogProbsContent] = deriveCodec[LogProbsContent]

