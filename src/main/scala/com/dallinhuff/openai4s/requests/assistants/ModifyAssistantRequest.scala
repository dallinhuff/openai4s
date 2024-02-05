package com.dallinhuff.openai4s.requests.assistants

import cats.effect.Async
import com.dallinhuff.openai4s.entities.assistants.ModifyAssistant
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.implicits.uri

class ModifyAssistantRequest[F[_]: Async](assistantId: String)
    extends OpenAIRequest[F, ModifyAssistant](
      Method.POST,
      uri"https://api.openai/com/v1/assistants" / assistantId
    )
