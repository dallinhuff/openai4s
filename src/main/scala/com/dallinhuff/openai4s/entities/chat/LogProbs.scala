package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.syntax.*

case class LogProbs(content: List[LogProbsContent])

object LogProbs:
  given Codec[LogProbs] = deriveCodec[LogProbs]
