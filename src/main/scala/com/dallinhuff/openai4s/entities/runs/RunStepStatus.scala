package com.dallinhuff.openai4s.entities.runs

enum RunStepStatus:
  case InProgress, Cancelled, Failed, Completed, Expired
