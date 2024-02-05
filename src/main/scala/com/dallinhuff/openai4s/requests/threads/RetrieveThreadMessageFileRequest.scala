package com.dallinhuff.openai4s.requests.threads

import cats.effect.Async
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.implicits.uri

class RetrieveThreadMessageFileRequest[F[_]: Async](
    threadId: String,
    messageId: String,
    fileId: String
) extends OpenAIRequest[F, Unit](
      Method.GET,
      uri"https://api.openai.com/v1/threads" / threadId / "messages" / messageId / "files" / fileId
    )
