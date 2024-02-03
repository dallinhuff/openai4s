package com.dallinhuff.openai4s.entities.threads

import io.circe.Decoder.Result
import io.circe.generic.semiauto.{deriveCodec, deriveDecoder}
import io.circe.{Codec, Decoder, DecodingFailure, Encoder, HCursor, Json}
import io.circe.syntax.*

enum ThreadMessageContent:
  case ImageFile(file_id: String)
  case Text(value: String, annotations: List[TextAnnotation])

object ThreadMessageContent:
  private given Codec[ThreadMessageContent.ImageFile] =
    deriveCodec[ThreadMessageContent.ImageFile]

  private given Codec[ThreadMessageContent.Text] =
    deriveCodec[ThreadMessageContent.Text]

  given Decoder[ThreadMessageContent] with
    override def apply(c: HCursor): Result[ThreadMessageContent] =
      c.downField("type")
        .as[String]
        .flatMap:
          case "image_file" =>
            c.downField("image_file").as[ThreadMessageContent.ImageFile]
          case "text" =>
            c.downField("text").as[ThreadMessageContent.Text]
          case _ =>
            Left(DecodingFailure("bad thread message content type", c.history))

  given Encoder[ThreadMessageContent] with
    override def apply(a: ThreadMessageContent): Json =
      a match
        case i: ThreadMessageContent.ImageFile =>
          Json.obj(
            "type"       -> Json.fromString("image_file"),
            "image_file" -> i.asJson
          )
        case t: ThreadMessageContent.Text =>
          Json.obj(
            "type" -> Json.fromString("text"),
            "text" -> t.asJson
          )

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

object TextAnnotation:
  given Decoder[TextAnnotation] with
    override def apply(c: HCursor): Result[TextAnnotation] =
      for
        tpe         <- c.downField("type").as[String]
        text        <- c.downField("text").as[String]
        start_index <- c.downField("start_index").as[Int]
        end_index   <- c.downField("end_index").as[Int]
        file_id     <- c.downField(tpe).downField("file_id").as[String]
        result <- tpe match
          case "file_citation" =>
            c.downField(tpe)
              .downField("quote")
              .as[String]
              .map: q =>
                TextAnnotation.FileCitation(
                  text,
                  start_index,
                  end_index,
                  file_id,
                  q
                )
          case "file_path" =>
            Right(
              TextAnnotation.FilePath(text, start_index, end_index, file_id)
            )
      yield result

  given Encoder[TextAnnotation] with
    override def apply(a: TextAnnotation): Json =
      a match
        case c: TextAnnotation.FileCitation =>
          Json.obj(
            "type"        -> Json.fromString("file_citation"),
            "text"        -> Json.fromString(c.text),
            "start_index" -> Json.fromInt(c.start_index),
            "end_index"   -> Json.fromInt(c.end_index),
            "file_citation" -> Json.obj(
              "file_id" -> Json.fromString(c.file_id),
              "quote"   -> Json.fromString(c.quote)
            )
          )
        case p: TextAnnotation.FilePath =>
          Json.obj(
            "type"        -> Json.fromString("file_path"),
            "text"        -> Json.fromString(p.text),
            "start_index" -> Json.fromInt(p.start_index),
            "end_index"   -> Json.fromInt(p.end_index),
            "file_path"   -> Json.obj("file_id" -> Json.fromString(p.file_id))
          )
