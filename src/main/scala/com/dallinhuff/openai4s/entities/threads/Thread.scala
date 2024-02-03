package com.dallinhuff.openai4s.entities.threads

import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.*

case class Thread(
    id: String,
    createdAt: Int,
    metadata: Map[String, String]
)

object Thread:
  given Decoder[Thread] = deriveDecoder[Thread]
  given Encoder[Thread] = deriveEncoder[Thread]
    .mapJson(_.deepMerge(Json.obj("object" -> "thread".asJson)))
    .mapJson(_.dropNullValues)
