package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.ChatMessage.Tool

case class CreateRun(
    assistant_id: String,
    model: Option[String] = None,
    instructions: Option[String] = None,
    additional_instructions: Option[String] = None,
    tools: Option[List[Tool]] = None,
    metadata: Option[Map[String, String]] = None
)
