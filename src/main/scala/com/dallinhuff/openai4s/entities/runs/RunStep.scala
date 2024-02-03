package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.Usage
import io.circe.Decoder.Result
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

enum RunStep(
    id: String,
    created_at: Int,
    assistant_id: String,
    thread_id: String,
    run_id: String,
    status: RunStepStatus,
    stepDetails: RunStepDetails,
    last_error: Option[RunError],
    expired_at: Option[Int],
    cancelled_at: Option[Int],
    failed_at: Option[Int],
    completed_at: Option[Int],
    metadata: Map[String, String],
    usage: Option[Usage]
):
  case MessageCreation(
      id: String,
      created_at: Int,
      assistant_id: String,
      thread_id: String,
      run_id: String,
      status: RunStepStatus,
      stepDetails: RunStepDetails,
      last_error: Option[RunError],
      expired_at: Option[Int],
      cancelled_at: Option[Int],
      failed_at: Option[Int],
      completed_at: Option[Int],
      metadata: Map[String, String],
      usage: Option[Usage]
  ) extends RunStep(
        id,
        created_at,
        assistant_id,
        thread_id,
        run_id,
        status,
        stepDetails,
        last_error,
        expired_at,
        cancelled_at,
        failed_at,
        completed_at,
        metadata,
        usage
      )
  case ToolCalls(
      id: String,
      created_at: Int,
      assistant_id: String,
      thread_id: String,
      run_id: String,
      status: RunStepStatus,
      stepDetails: RunStepDetails,
      last_error: Option[RunError],
      expired_at: Option[Int],
      cancelled_at: Option[Int],
      failed_at: Option[Int],
      completed_at: Option[Int],
      metadata: Map[String, String],
      usage: Option[Usage]
  ) extends RunStep(
        id,
        created_at,
        assistant_id,
        thread_id,
        run_id,
        status,
        stepDetails,
        last_error,
        expired_at,
        cancelled_at,
        failed_at,
        completed_at,
        metadata,
        usage
      )

object RunStep:
  given Decoder[RunStep] with
    override def apply(c: HCursor): Result[RunStep] =
      c.downField("type")
        .as[String]
        .flatMap:
          case "message_creation" => derivedMsgCreationDecoder(c)
          case "tool_calls"       => derivedToolCallsDecoder(c)
          case _ => Left(DecodingFailure("bad run step type", c.history))

  private val derivedMsgCreationDecoder = deriveDecoder[RunStep.MessageCreation]
  private val derivedToolCallsDecoder = deriveDecoder[RunStep.ToolCalls]
  
  given Encoder[RunStep] with
    override def apply(a: RunStep): Json =
      val tpe = a match
        case _: RunStep.MessageCreation => "message_creation"
        case _: RunStep.ToolCalls       => "tool_calls"
      derivedEncoder(a).deepMerge(Json.obj("type" -> Json.fromString(tpe)))

  private val derivedEncoder = deriveEncoder[RunStep]
