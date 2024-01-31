package com.dallinhuff.openai4s.completions

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class ChunkChoice(
  delta: Delta,
  logprobs: Option[LogProbs],
  finish_reason: Option[String],
  index: Integer
)

object ChunkChoice:
  given Codec[ChunkChoice] = deriveCodec[ChunkChoice]
