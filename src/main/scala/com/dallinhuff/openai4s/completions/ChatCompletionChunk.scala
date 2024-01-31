package com.dallinhuff.openai4s.completions

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class ChatCompletionChunk(
  id: String,
  choices: List[ChunkChoice],
  created: Int,
  model: String,
  system_fingerprint: String,
  `object`: String
)

object ChatCompletionChunk:
  given Codec[ChatCompletionChunk] = deriveCodec[ChatCompletionChunk]
