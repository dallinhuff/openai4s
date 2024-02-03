package com.dallinhuff.openai4s.entities.threads

enum ThreadMessageContent:
  case ImageFile(file_id: String)
  case Text(value: String, annotations: List[TextAnnotation])

enum TextAnnotation(text: String, start_index: Int, end_index: Int):
  case FileCitation(
      text: String,
      start_index: Int,
      end_index: Int,
      file_id: String,
      quote: String
  ) extends TextAnnotation(text, start_index, end_index)
  case FilePath(
      text: String,
      start_index: Int,
      end_index: Int,
      file_id: String
  ) extends TextAnnotation(text, start_index, end_index)
