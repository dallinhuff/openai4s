package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.ToolCall
import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.syntax.*

enum RequiredAction:
  case SubmitToolOutputs(toolCalls: List[ToolCall])

object RequiredAction:
  given Decoder[RequiredAction] with
    override def apply(c: HCursor): Result[RequiredAction] =
      for
        _ <- c
          .downField("type")
          .as[String]
          .ensuring(_ == Right("submit_tool_outputs"))
        toolCalls <- c.downField("tool_calls").as[List[ToolCall]]
      yield RequiredAction.SubmitToolOutputs(toolCalls)
      
  given Encoder[RequiredAction] with
    override def apply(a: RequiredAction): Json =
      a match
        case RequiredAction.SubmitToolOutputs(toolCalls) =>
          Json.obj(
            "type" -> Json.fromString("submit_tool_outputs"),
            "submit_tool_calls" -> toolCalls.asJson
          )
