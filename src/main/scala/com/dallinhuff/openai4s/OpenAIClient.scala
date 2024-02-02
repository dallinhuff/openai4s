package com.dallinhuff.openai4s

import cats.effect.{Async, IO}
import com.dallinhuff.openai4s.auth.OpenAIKey
import com.dallinhuff.openai4s.completions.{ChatCompletion, CompletionRequest, CreateChatCompletion}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.circe.CirceEntityDecoder.circeEntityDecoder

object OpenAIClient:
  def clientResource[F[_]: Async] =
    EmberClientBuilder
      .default[F]
      .build

  def getChatCompletion(createChatCompletion: CreateChatCompletion)(using apiKey: OpenAIKey): IO[ChatCompletion] =
    clientResource[IO].use: client =>
      client.expect[ChatCompletion](CompletionRequest(createChatCompletion))

