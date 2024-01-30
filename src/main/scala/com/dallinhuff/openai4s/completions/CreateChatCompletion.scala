package com.dallinhuff.openai4s.completions

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*
import io.circe.syntax.*

case class CreateChatCompletion(
  model: String,
  messages: List[Message],
  frequency_penalty: Option[Double] = None,
  logit_bias: Option[Map[String, Double]] = None,
  logprobs: Option[Boolean] = None,
  top_logprobs: Option[Int] = None,
  max_tokens: Option[Int] = None,
  n: Option[Int] = None,
  presence_penalty: Option[Double] = None,
  response_format: Option[ResponseFormat] = None,
  seed: Option[Int] = None,
  stop: Option[List[String]] = None,
  stream: Option[Boolean] = None,
  temperature: Option[Double] = None,
  top_p: Option[Double] = None,
  tools: Option[List[Tool]] = None,
  tool_choice: Option[ToolChoice] = None,
  user: Option[String] = None
)

object CreateChatCompletion:
  given Codec[CreateChatCompletion] with
    override def apply(a: CreateChatCompletion): Json =
      deriveEncoder[CreateChatCompletion](a).asJson.dropNullValues
    override def apply(c: HCursor): Result[CreateChatCompletion] =
      deriveDecoder[CreateChatCompletion](c)


