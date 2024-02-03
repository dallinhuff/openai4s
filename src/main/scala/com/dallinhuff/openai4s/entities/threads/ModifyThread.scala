package com.dallinhuff.openai4s.entities.threads

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ModifyThread(metadata: Option[Map[String, String]] = None)

object ModifyThread:
  given Decoder[ModifyThread] =
    deriveDecoder[ModifyThread]

  given Encoder[ModifyThread] =
    deriveEncoder[ModifyThread]
      .mapJson(_.dropNullValues)
