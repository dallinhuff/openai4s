package com.dallinhuff.openai4s.entities.chat

import io.circe.Json
import io.circe.syntax.*
import org.scalatest.wordspec.AnyWordSpec

class ChatChoiceSpec extends AnyWordSpec:
  val msg        = ChatMessage.Assistant(Some("I finished"))
  val chatChoice = ChatChoice("stop", 0, msg, None)

  val json = Json.obj(
    "finish_reason" -> "stop".asJson,
    "index"         -> 0.asJson,
    "message" -> msg.asJson
  )

  "ChatChoice" should:
    "serialize to OpenAI API" in:
      assert(chatChoice.asJson == json)

    "deserialize from OpenAI API" in:
      assert(json.as[ChatChoice] == Right(chatChoice))
