package com.dallinhuff.openai4s.requests.threads

import cats.effect.Async
import com.dallinhuff.openai4s.requests.OpenAIRequest
import org.http4s.Method
import org.http4s.implicits.uri

class ListThreadMessagesRequest[F[_]: Async](
    threadId: String,
    limit: Option[Int] = None,
    order: Option[String] = None,
    after: Option[String] = None,
    before: Option[String] = None
) extends OpenAIRequest[F, Unit](
      Method.GET,
      uri"https://api.openai.com/v1/threads" / threadId / "messages"
        +?? ("limit"  -> limit)
        +?? ("order"  -> order)
        +?? ("after"  -> after)
        +?? ("before" -> before)
    )
