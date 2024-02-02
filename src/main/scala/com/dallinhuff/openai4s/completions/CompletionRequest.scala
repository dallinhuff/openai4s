package com.dallinhuff.openai4s.completions

import com.dallinhuff.openai4s.auth.OpenAIKey
import io.circe.syntax.*
import org.http4s.headers.{Authorization, `Content-Type`}
import org.http4s.{AuthScheme, Credentials, Headers, HttpVersion, MediaType, Method, Request}
import org.http4s.syntax.all.*
import org.http4s.circe.*

object CompletionRequest:
  def apply[F[_]](createChatCompletion: CreateChatCompletion)(using apiKey: OpenAIKey): Request[F] =
    Request[F](
      method = Method.POST,
      uri = uri"https://api.openai.com/v1/chat/completions",
      httpVersion = HttpVersion.`HTTP/1.1`,
      headers = Headers(
        Authorization(Credentials.Token(AuthScheme.Bearer, apiKey.key)),
        `Content-Type`(MediaType.application.json)
      )
    ).withEntity(createChatCompletion.asJson)
    
  
