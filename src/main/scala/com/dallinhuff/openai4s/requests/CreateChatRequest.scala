package com.dallinhuff.openai4s.requests

import com.dallinhuff.openai4s.entities.chat.CreateChat
import org.http4s.Method
import org.http4s.syntax.all.uri

object CreateChatRequest
    extends OpenAIRequest[CreateChat](
      Method.POST,
      uri"https://api.openai.com/v1/chat/completions"
    )
