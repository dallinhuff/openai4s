package com.dallinhuff.openai4s

import cats.effect.{IO, IOApp}
import com.dallinhuff.openai4s.auth.OpenAIKey
import com.dallinhuff.openai4s.entities.assistants.Assistant
import com.dallinhuff.openai4s.entities.chat.{ChatMessage, CreateChat}
import com.dallinhuff.openai4s.requests.OpenAIRequest
import com.dallinhuff.openai4s.requests.assistants.ListAssistantsRequest
import com.dallinhuff.openai4s.requests.chat.CreateChatRequest

object Main extends IOApp.Simple:
  override val run: IO[Unit] =
    for
      key <- IO.pure:
        OpenAIKey("") // TODO: load this in from config
      req <- IO.pure:
        CreateChat(
          model = "gpt-3.5-turbo",
          messages = ChatMessage.User("tell me a joke") :: Nil
        )
      res <- OpenAIClient[IO]().createChat(req)(using key)
      msg <- IO.pure:
        res.choices.head.message match
          case ChatMessage.Assistant(Some(s), _, _) =>
            s
          case _ =>
            ""
    yield ()
