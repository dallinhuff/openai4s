package com.dallinhuff.openai4s.requests

import cats.effect.Async
import com.dallinhuff.openai4s.auth.OpenAIKey
import io.circe.Codec
import io.circe.syntax.*
import org.http4s.{
  AuthScheme,
  Credentials,
  Headers,
  HttpVersion,
  MediaType,
  Method,
  Request,
  Uri
}
import org.http4s.circe.*
import org.http4s.headers.{Authorization, `Content-Type`}

trait OpenAIRequest[E: Codec](
    method: Method,
    uri: Uri,
    headers: Headers = Headers()
):
  def apply[F[_]: Async](entity: E)(using apiKey: OpenAIKey): Request[F] =
    Request[F](
      method = method,
      uri = uri,
      httpVersion = HttpVersion.`HTTP/1.1`,
      headers = headers ++ Headers(
        Authorization(Credentials.Token(AuthScheme.Bearer, apiKey.key)),
        `Content-Type`(MediaType.application.json)
      )
    ).withEntity(entity.asJson)
