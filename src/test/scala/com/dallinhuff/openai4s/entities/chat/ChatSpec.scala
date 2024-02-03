package com.dallinhuff.openai4s.entities.chat

import io.circe.Json
import io.circe.syntax.*
import org.scalatest.wordspec.AnyWordSpec

class ChatSpec extends AnyWordSpec:
  val chat = Chat(
    "myChat",
    List(),
    0,
    "gpt-4-turbo",
    None,
    Usage(0, 0, 0)
  )

  val json = Json.obj(
    "id" -> "myChat".asJson,
    "choices" -> List[ChatChoice]().asJson,
    "created" -> 0.asJson,
    "model" -> "gpt-4-turbo".asJson,
    "object" -> "chat.completion".asJson,
    "usage" -> Json.obj(
      "completion_tokens" -> 0.asJson,
      "prompt_tokens" -> 0.asJson,
      "total_tokens" -> 0.asJson
    )
  )

  "Chat" should:
    "serialize to OpenAI API" in:
      assert(chat.asJson == json)

    "deserialize from OpenAI API" in:
      assert(json.as[Chat] == Right(chat))
