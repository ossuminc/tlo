import sbt.*

object V {
  val riddl = "0.39.0"
}

object Deps {
  val riddlTest = Seq(
    "com.ossuminc" %% "riddl-testkit" % V.riddl  % "test",
    "com.ossuminc" %% "riddl-hugo" % V.riddl % "test",
    "org.scalactic" %% "scalactic" % "3.2.9" % "test",
    "org.scalatest" %% "scalatest" % "3.2.9" % "test"
  )
}
