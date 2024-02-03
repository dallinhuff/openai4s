package com.dallinhuff.openai4s.entities.threads

case class ThreadMessage(
    id: String,
    created_at: Int,
    thread_id: String,
    role: String,
    content: List[ThreadMessageContent],
    assistant_id: Option[String],
    run_id: Option[String],
    file_ids: List[String],
    metadata: Map[String, String]
)
