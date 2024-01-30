package com.dallinhuff.openai4s

import com.dallinhuff.openai4s.completions.{CreateChatCompletion, Message}
import io.circe.*
import io.circe.syntax.*

object Main extends App {
  val req = CreateChatCompletion(
    "my-model",
    List(
      Message.SystemMessage("bu"),
      Message.UserMessage("bruh"),
      Message.ToolMessage("bro", "ddd")
    )
  )
  println(req.asJson.toString)
}
