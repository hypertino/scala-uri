// Sonatype credentials
credentials ++= (for {
  username <- Option(System.getenv().get("sonatype_username"))
  password <- Option(System.getenv().get("sonatype_password"))
} yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)).toSeq

// pgp keys and credentials
pgpSecretRing := file("./travis/ht-oss-private.asc")
pgpPublicRing := file("./travis/ht-oss-public.asc")
usePgpKeyHex("F8CDEF49B0EDEDCC")
pgpPassphrase := Option(System.getenv().get("oss_gpg_passphrase")).map(_.toCharArray)
