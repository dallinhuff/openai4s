package com.dallinhuff.openai4s.entities.threads

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ModifyThreadMessage(metadata: Option[Map[String, String]] = None)

object ModifyThreadMessage:
  given Decoder[ModifyThreadMessage] = deriveDecoder[ModifyThreadMessage]

  given Encoder[ModifyThreadMessage] = deriveEncoder[ModifyThreadMessage]
    .mapJson(_.dropNullValues)
