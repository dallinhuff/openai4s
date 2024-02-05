package com.dallinhuff.openai4s

import cats.effect.Async
import com.dallinhuff.openai4s.auth.OpenAIKey
import com.dallinhuff.openai4s.entities.chat.{Chat, CreateChat}
import com.dallinhuff.openai4s.requests.chat.CreateChatRequest
import org.http4s.circe.CirceEntityDecoder.circeEntityDecoder
import org.http4s.ember.client.EmberClientBuilder

class OpenAIClient[F[_]: Async]:
  def clientResource =
    EmberClientBuilder
      .default[F]
      .build

  def createChat(entity: CreateChat)(using OpenAIKey): F[Chat] =
    clientResource.use: client =>
      val req = CreateChatRequest[F]()
      client.expect[Chat](req(entity))
