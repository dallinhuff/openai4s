package com.dallinhuff.openai4s.requests.assistants

import cats.effect.Async
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.syntax.all.uri

class RetrieveAssistantFileRequest[F[_]: Async](assistantId: String, fileId: String)
    extends OpenAIRequest[F, Unit](
      Method.GET,
      uri"https://api.openai.com/v1/assistants" / assistantId / "files" / fileId
    )
