package com.dallinhuff.openai4s.entities.assistants

import com.dallinhuff.openai4s.entities.chat.Tool
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ModifyAssistant(
    model: Option[String] = None,
    name: Option[String] = None,
    description: Option[String] = None,
    instructions: Option[String] = None,
    tools: Option[List[Tool]] = None,
    file_ids: Option[List[String]] = None,
    metadata: Option[Map[String, String]] = None
)

object ModifyAssistant:
  given Decoder[ModifyAssistant] = deriveDecoder[ModifyAssistant]

  given Encoder[ModifyAssistant] = deriveEncoder[ModifyAssistant]
    .mapJson(_.dropNullValues)
