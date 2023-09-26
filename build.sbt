import Dependencies._

ThisBuild / organization := "me.julian_a_avar_c"
ThisBuild / scalaVersion := "3.3.1"

lazy val godot_bindings =
  project
    .in(file("godot_bindings"))
    .settings(name := "godot_bindings")
    .settings(commonSettings)
    .settings(autoImportSettings)
    .settings(dependencies)

lazy val godot_sc =
  project
    .in(file("."))
    .settings(name := "godot_sc")
    .settings(commonSettings)
    .settings(autoImportSettings)
    .settings(dependencies)

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions := {
      (Compile / console / scalacOptions)
        .value
        .filterNot(_.contains("wartremover"))
        .filterNot(Scalac.Lint.toSet)
        .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
    },
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val autoImportSettings = Seq(
  scalacOptions +=
    Seq(
      "java.lang",
      "scala",
      "scala.Predef",
      "scala.annotation",
      "scala.util.chaining",
    ).mkString(start = "-Yimports:", sep = ",", end = ""),
  Test / scalacOptions +=
    Seq(
      "org.scalacheck",
      "org.scalacheck.Prop",
    ).mkString(start = "-Yimports:", sep = ",", end = ""),
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    // main dependencies
  ),
  libraryDependencies ++= Seq(
    com.eed3si9n.expecty.expecty,
    org.scalacheck.scalacheck,
    org.scalameta.`munit-scalacheck`,
    org.scalameta.munit,
    org.typelevel.`discipline-munit`,
  ).map(_ % Test),
)
