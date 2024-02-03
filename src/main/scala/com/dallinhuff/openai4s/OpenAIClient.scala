package com.dallinhuff.openai4s

import cats.effect.{Async, IO}
import com.dallinhuff.openai4s.auth.OpenAIKey
import com.dallinhuff.openai4s.entities.chat.{Chat, CreateChat}
import com.dallinhuff.openai4s.requests.{CreateChatRequest, OpenAIRequest}
import io.circe.Codec
import org.http4s.circe.CirceEntityDecoder.circeEntityDecoder
import org.http4s.ember.client.EmberClientBuilder

object OpenAIClient:
  def clientResource[F[_]: Async] =
    EmberClientBuilder
      .default[F]
      .build

  def getChatCompletion(
      createChatCompletion: CreateChat
  )(using apiKey: OpenAIKey): IO[Chat] =
    clientResource[IO].use: client =>
      client.expect[Chat](CreateChatRequest[IO](createChatCompletion))
