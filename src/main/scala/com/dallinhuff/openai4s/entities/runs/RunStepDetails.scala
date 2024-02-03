package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.ToolCall

enum RunStepDetails:
  case MessageCreation(message_id: String)
  case ToolCalls(calls: List[ToolCall])
