# openai4s (OpenAI For Scala)

This project aims to provide type-safe request & response bindings for OpenAI's public APIs.

Currently, bindings exist for the new [completions]() API, with more to come.

Future releases will include http clients for sending and receiving requests,
but for now this library is primarily concerned with providing safe serialization & deserialization
of request bodies, where request handling can be handled by another library.

## Installation

#### sbt
`com.dallinhuff %% openai4s % 0.1.1-SNAPSHOT`

## Usage

```scala
import com.dallinhuff.openai4s.completions.*

//...

// CreateChatCompletion and ChatCompletion have circe Codecs,
// so you can serialize them in HTTP requests/responses using
// whichever http client library you prefer
val prompt = CreateChatCompletion(
  model = "gpt-3.5-turbo",
  messages = List(
    Message.SystemMessage("you are a cranky old man who never tells jokes"),
    Message.UserMessage("tell me a joke please")
  ),
  n = Some(2)
)
```