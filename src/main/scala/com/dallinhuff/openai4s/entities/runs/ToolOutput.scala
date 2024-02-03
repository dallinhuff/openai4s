package com.dallinhuff.openai4s.entities.runs

case class ToolOutput(
    tool_call_id: Option[String] = None,
    output: Option[String] = None
)
