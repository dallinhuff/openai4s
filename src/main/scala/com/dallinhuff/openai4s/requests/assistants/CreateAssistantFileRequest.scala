package com.dallinhuff.openai4s.requests.assistants

import cats.effect.Async
import com.dallinhuff.openai4s.entities.assistants.CreateAssistantFile
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.syntax.all.uri

class CreateAssistantFileRequest[F[_] : Async](assistantId: String)
  extends OpenAIRequest[F, CreateAssistantFile](
    Method.POST,
    uri"https://api.openai.com/v1/assistants" / assistantId / "files"
  )
