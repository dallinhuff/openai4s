package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

case class CreateChat(
    model: String,
    messages: List[ChatMessage],
    frequency_penalty: Option[Double] = None,
    logit_bias: Option[Map[String, Double]] = None,
    logprobs: Option[Boolean] = None,
    top_logprobs: Option[Int] = None,
    max_tokens: Option[Int] = None,
    n: Option[Int] = None,
    presence_penalty: Option[Double] = None,
    response_format: Option[ChatResponseFormat] = None,
    seed: Option[Int] = None,
    stop: Option[List[String]] = None,
    stream: Option[Boolean] = None,
    temperature: Option[Double] = None,
    top_p: Option[Double] = None,
    tools: Option[List[Tool]] = None,
    tool_choice: Option[ToolChoice] = None,
    user: Option[String] = None
)

object CreateChat:
  given Codec[CreateChat] with
    override def apply(a: CreateChat): Json =
      deriveEncoder[CreateChat](a).asJson.dropNullValues
    override def apply(c: HCursor): Result[CreateChat] =
      deriveDecoder[CreateChat](c)
