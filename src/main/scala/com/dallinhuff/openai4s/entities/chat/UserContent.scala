package com.dallinhuff.openai4s.entities.chat

import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.syntax.*

enum UserContent:
  case Text(text: String)
  case ImageUrl(url: String, detail: Option[String] = None)

object UserContent:
  private given txtCodec: Codec[UserContent.Text] =
    deriveCodec[UserContent.Text]

  private given imgCodec: Codec[UserContent.ImageUrl] =
    deriveCodec[UserContent.ImageUrl]

  given Codec[UserContent] with
    override def apply(a: UserContent): Json =
      a match
        case t: UserContent.Text =>
          t.asJson.deepMerge(Json.obj("type" -> Json.fromString("text")))
        case i: UserContent.ImageUrl =>
          i.asJson.deepMerge(Json.obj("type" -> Json.fromString("image_url")))

    override def apply(c: HCursor): Decoder.Result[UserContent] =
      c.downField("type")
        .as[String]
        .flatMap:
          case "text"      => txtCodec(c)
          case "image_url" => imgCodec(c)
          case _           => Left(DecodingFailure("bad type", c.history))
  end given
