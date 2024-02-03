package com.dallinhuff.openai4s.entities.runs

import com.dallinhuff.openai4s.entities.chat.Usage

case class RunStep(
    id: String,
    created_at: Int,
    assistant_id: String,
    thread_id: String,
    run_id: String,
    `type`: String,      // TODO enum
    status: String,      // TODO enum
    stepDetails: String, // TODO
    last_error: Option[RunError],
    expired_at: Option[Int],
    cancelled_at: Option[Int],
    failed_at: Option[Int],
    completed_at: Option[Int],
    metadata: Map[String, String],
    usage: Option[Usage]
)
