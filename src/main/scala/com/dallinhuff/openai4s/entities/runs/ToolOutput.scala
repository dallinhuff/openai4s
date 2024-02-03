package com.dallinhuff.openai4s.entities.runs

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ToolOutput(
    tool_call_id: Option[String] = None,
    output: Option[String] = None
)

object ToolOutput:
  given Decoder[ToolOutput] = deriveDecoder[ToolOutput]
  given Encoder[ToolOutput] = deriveEncoder[ToolOutput]
    .mapJson(_.dropNullValues)
