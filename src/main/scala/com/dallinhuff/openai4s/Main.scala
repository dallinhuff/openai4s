package com.dallinhuff.openai4s

import cats.effect.{IO, IOApp}
import com.dallinhuff.openai4s.auth.OpenAIKey
import com.dallinhuff.openai4s.entities.chat.{ChatMessage, CreateChat}
import com.dallinhuff.openai4s.requests.{CreateChatRequest, OpenAIRequest}

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
      res <- OpenAIClient.getChatCompletion(req)(using key)
      _   <- IO.println(res.choices.head.message.content.get)
    yield ()
