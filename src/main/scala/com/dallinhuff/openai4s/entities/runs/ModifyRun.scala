package com.dallinhuff.openai4s.entities.runs

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ModifyRun(
    metadata: Option[Map[String, String]] = None
)

object ModifyRun:
  given Decoder[ModifyRun] = deriveDecoder[ModifyRun]
  given Encoder[ModifyRun] = deriveEncoder[ModifyRun]
    .mapJson(_.dropNullValues)
