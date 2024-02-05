package com.dallinhuff.openai4s.requests.threads

import cats.effect.Async
import com.dallinhuff.openai4s.entities.threads.CreateThread
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.implicits.uri

class CreateThreadRequest[F[_]: Async]
    extends OpenAIRequest[F, CreateThread](
      Method.POST,
      uri"https://api.openai.com/v1/threads"
    )
