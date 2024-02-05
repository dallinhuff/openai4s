package com.dallinhuff.openai4s.requests.threads

import cats.effect.Async
import com.dallinhuff.openai4s.entities.threads.ModifyThreadMessage
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.implicits.uri

class ModifyThreadMessageRequest[F[_]: Async](
    threadId: String,
    messageId: String
) extends OpenAIRequest[F, ModifyThreadMessage](
      Method.POST,
      uri"https://api.openai.com/v1/threads" / threadId / "messages" / messageId
    )
