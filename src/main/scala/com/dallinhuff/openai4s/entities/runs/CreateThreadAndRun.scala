package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.ChatMessage.Tool
import com.dallinhuff.openai4s.entities.threads.CreateThread
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class CreateThreadAndRun(
    assistant_id: String,
    thread: Option[CreateThread] = None,
    model: Option[String] = None,
    instructions: Option[String] = None,
    tools: Option[List[Tool]] = None,
    metadata: Option[Map[String, String]] = None
)

object CreateThreadAndRun:
  given Decoder[CreateThreadAndRun] = deriveDecoder[CreateThreadAndRun]
  given Encoder[CreateThreadAndRun] = deriveEncoder[CreateThreadAndRun]
    .mapJson(_.dropNullValues)
