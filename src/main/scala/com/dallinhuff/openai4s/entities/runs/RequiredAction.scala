package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.ToolCall

enum RequiredAction:
  case SubmitToolOutputs(toolCalls: List[ToolCall])
