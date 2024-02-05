package com.dallinhuff.openai4s.requests.threads

import cats.effect.Async
import com.dallinhuff.openai4s.entities.threads.ModifyThread
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.implicits.uri

class ModifyThreadRequest[F[_]: Async](threadId: String)
    extends OpenAIRequest[F, ModifyThread](
      Method.POST,
      uri"https://api.openai.com/v1/threads" / threadId
    )
