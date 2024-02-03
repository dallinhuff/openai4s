package com.dallinhuff.openai4s.entities.runs

enum RunToolCall(id: String, `type`: String):
  case CodeInterpreter(
      id: String,
      input: String,
      outputs: List[CodeInterpreterOutput]
  ) extends RunToolCall(id, "code_interpreter")
  case Retrieval(id: String, retrieval: Map[String, String])
      extends RunToolCall(id, "retrieval")
  case Function(
      id: String,
      name: String,
      arguments: String,
      output: Option[String]
  ) extends RunToolCall(id, "function")

enum CodeInterpreterOutput:
  case Log(logs: String)
  case Image(file_id: String)
