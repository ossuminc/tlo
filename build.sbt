import sbt.Keys.*
import com.ossuminc.sbt.helpers.Publishing
import com.ossuminc.sbt.helpers.RootProjectInfo.Keys.*

Global / onChangedBuildSource := ReloadOnSourceChanges
(Global / excludeLintKeys) ++= Set(mainClass)
Global / scalaVersion := "3.3.3"

enablePlugins(OssumIncPlugin)

lazy val root: Project = Root("", "tlo", startYr = 2019)
  .configure(Publishing.configure, With.git, With.dynver)
  .settings(
    gitHubRepository := "tlo",
    gitHubOrganization := "ossuminc",
    publish / skip := true
  )
  .aggregate(design, api, gateway)

lazy val api = Module("api", "tlo-api")
  .enablePlugins(OssumIncPlugin)
  .configure(With.typical)
  .configure(With.coverage(90))

lazy val gateway = Module("gateway", "tlo-gateway")
  .enablePlugins(OssumIncPlugin)
  .configure(With.typical)
  .configure(With.coverage(90))

lazy val design = Module("design", "tlo-design")
  .enablePlugins(OssumIncPlugin)
  .enablePlugins(RiddlSbtPlugin)
  .configure(With.typical)
  .configure(With.coverage(90))
  .settings(
    libraryDependencies ++= Deps.riddlTest,
    riddlcOptions := Seq(
      "--show-times",
      "from",
      "design/src/main/riddl/TLO.conf",
      "hugo"
    ),
    riddlcMinVersion := s"${V.riddl}",
    riddlcPath := Some(
      file(
        // NOTE: Set this to your local path which will always have this portion
        // NOTE: of the path as a constant: riddl/riddlc/target/universal/stage/bin/riddlc
        // NOTE: You must "sbt stage" in the riddl/riddlc directory for this to work
        "/Users/reid/Code/Ossum/riddl/riddlc/target/universal/stage/bin/riddlc"
      )
    )
  )
