package com.dallinhuff.openai4s.entities.runs

enum RunError(code: String, message: String):
  case ServerError(message: String) extends RunError("server_error", message)
  case RateLimitExceeded(message: String)
      extends RunError("rate_limit_exceeded", message)
