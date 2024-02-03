package com.dallinhuff.openai4s.entities.runs

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class SubmitToolOutputsToRun(
    tool_outputs: List[ToolOutput]
)

object SubmitToolOutputsToRun:
  given Codec[SubmitToolOutputsToRun] = deriveCodec[SubmitToolOutputsToRun]
