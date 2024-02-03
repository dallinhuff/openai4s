package com.dallinhuff.openai4s.entities.assistants

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class CreateAssistantFile(file_id: String)

object CreateAssistantFile:
  given Codec[CreateAssistantFile] = deriveCodec[CreateAssistantFile]
