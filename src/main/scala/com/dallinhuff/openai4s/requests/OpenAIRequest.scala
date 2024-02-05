package com.dallinhuff.openai4s.requests

import cats.effect.Async
import com.dallinhuff.openai4s.auth.OpenAIKey
import io.circe.Encoder
import io.circe.syntax.*
import org.http4s.{AuthScheme, Credentials, Headers, HttpVersion, MediaType, Method, Request, Uri}
import org.http4s.circe.*
import org.http4s.headers.{Authorization, `Content-Type`}

trait OpenAIRequest[F[_]: Async, E: Encoder](
    method: Method,
    uri: Uri,
    headers: Headers = Headers()
):
  def apply(entity: E)(using apiKey: OpenAIKey): Request[F] =
    Request[F](
      method = method,
      uri = uri,
      httpVersion = HttpVersion.`HTTP/1.1`,
      headers = headers ++ Headers(
        Authorization(Credentials.Token(AuthScheme.Bearer, apiKey.key)),
        `Content-Type`(MediaType.application.json)
      )
    ).withEntity(entity.asJson)
