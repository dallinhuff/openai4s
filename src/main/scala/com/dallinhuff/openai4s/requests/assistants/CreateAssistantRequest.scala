package com.dallinhuff.openai4s.requests.assistants

import cats.effect.Async
import com.dallinhuff.openai4s.entities.assistants.CreateAssistant
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.syntax.all.uri

class CreateAssistantRequest[F[_] : Async]
    extends OpenAIRequest[F, CreateAssistant](
      Method.POST,
      uri"https://api.openai.com/v1/assistants"
    )
