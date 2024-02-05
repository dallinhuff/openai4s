package com.dallinhuff.openai4s.requests.chat

import cats.effect.Async
import com.dallinhuff.openai4s.entities.chat.CreateChat
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.syntax.all.uri

class CreateChatRequest[F[_]: Async]
    extends OpenAIRequest[F, CreateChat](
      Method.POST,
      uri"https://api.openai.com/v1/chat/completions"
    )
