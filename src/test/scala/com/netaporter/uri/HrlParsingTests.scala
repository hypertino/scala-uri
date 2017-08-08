package com.netaporter.uri

import com.netaporter.uri.Uri._
import com.netaporter.uri.config.UriConfig
import com.netaporter.uri.encoding.PercentEncoder
import com.netaporter.uri.encoding.PercentEncoder.PATH_CHARS_TO_ENCODE
import org.scalatest.{FlatSpec, Matchers}

class HrlParsingTests extends FlatSpec with Matchers {

  "Parsing an HRL with parameters" should "result in a valid Uri object" in {
    val HRL_PATH_CHARS_TO_ENCODE = PATH_CHARS_TO_ENCODE -- Set ('{','}')
    implicit val default = UriConfig.default.copy(pathEncoder = PercentEncoder(HRL_PATH_CHARS_TO_ENCODE))

    val uri = parse("http://example.com/users/{userId}")
    uri.scheme should equal (Some("http"))
    uri.host should equal (Some("example.com"))
    uri.path should equal ("/users/{userId}")
  }

}