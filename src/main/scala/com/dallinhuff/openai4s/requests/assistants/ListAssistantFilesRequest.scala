package com.dallinhuff.openai4s.requests.assistants

import cats.effect.Async
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.syntax.all.uri

class ListAssistantFilesRequest[F[_]: Async](
    assistantId: String,
    limit: Option[Int] = None,
    order: Option[String] = None,
    after: Option[String] = None,
    before: Option[String] = None
) extends OpenAIRequest[F, Unit](
      Method.GET,
      uri"https://api.openai.com/v1/assistants" / assistantId
        +?? ("limit", limit)
        +?? ("order", order)
        +?? ("after", after)
        +?? ("before", before)
    )
