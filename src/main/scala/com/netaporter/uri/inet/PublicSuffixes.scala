package com.netaporter.uri.inet

import com.hypertino.binders.value.Value

import scala.io.Source

object PublicSuffixes {
  lazy val trie = {
    import com.hypertino.binders.json.JsonBinders._
    val trieJson = Source.fromURL(this.getClass.getResource("/public_suffix_trie.json"), "UTF-8")
    val trie = trieJson.mkString.parseJson[Value]
    trieJson.close()
    valueToTrie(trie)
  }

  private def valueToTrie(content: Value): Trie = {
    Trie(
      content.dynamic.c.toMap.map { kv ⇒
        kv._1.charAt(0) → valueToTrie(kv._2)
      }.toMap,
      content.dynamic.e.toBoolean
    )
  }
}

