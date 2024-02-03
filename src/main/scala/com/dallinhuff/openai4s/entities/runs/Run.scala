package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.{Tool, Usage}

case class Run(
    id: String,
    created_at: Int,
    thread_id: String,
    assistant_id: String,
    status: String,
    required_action: Option[RequiredAction],
    last_error: Option[RunError],
    expires_at: Int,
    started_at: Option[Int],
    cancelled_at: Option[Int],
    failed_at: Option[Int],
    completed_at: Option[Int],
    model: String,
    instructions: String,
    tools: List[Tool],
    file_ids: List[String],
    metadata: Map[String, String],
    usage: Option[Usage]
)
