package com.dallinhuff.openai4s.entities.threads

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class CreateThread(
    messages: Option[List[CreateThreadMessage]] = None,
    metadata: Option[Map[String, String]] = None
)

object CreateThread:
  given Encoder[CreateThread] =
    deriveEncoder[CreateThread]
      .mapJson(_.dropNullValues)

  given Decoder[CreateThread] =
    deriveDecoder[CreateThread]
