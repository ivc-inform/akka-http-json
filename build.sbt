// *****************************************************************************
// Projects
// *****************************************************************************



lazy val `akka-http-json` =
    project
      .in(file("."))
      //.enablePlugins(GitVersioning)
      .aggregate(
          `akka-http-argonaut`,
          `akka-http-circe`,
          `akka-http-jackson`,
          `akka-http-json4s`,
          `akka-http-play-json`,
          `akka-http-upickle`,
          `akka-http-avro4s`
      )
      .settings(settings)
      .settings(
          unmanagedSourceDirectories.in(Compile) := Seq.empty,
          unmanagedSourceDirectories.in(Test) := Seq.empty,
          publishArtifact := false
      )

lazy val `akka-http-argonaut` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.argonaut,
              library.scalaTest % Test
          )
      )

lazy val `akka-http-circe` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.circeCore,
              library.circeGeneric,
              library.circeJawn,
              library.circeJava8,
              library.circeParser,
              library.scalaTest % Test
          )
      )

lazy val `akka-http-jackson` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.akkaHttpJacksonJava,
              library.jacksonScala,
              library.scalaTest % Test
          )
      )

lazy val `akka-http-json4s` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.json4sCore,
              library.json4sJackson % Test,
              library.json4sNative % Test,
              library.scalaTest % Test
          )
      )

lazy val `akka-http-play-json` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.playJson,
              library.scalaTest % Test
          )
      )

lazy val `akka-http-upickle` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.upickle,
              library.scalaTest % Test
          )
      )

lazy val `akka-http-avro4s` =
    project
      .enablePlugins(AutomateHeaderPlugin)
      .settings(settings)
      .settings(
          libraryDependencies ++= Seq(
              library.akkaHttp,
              library.avro4sJson,
              library.avro4sCore,
              library.scalaTest % Test
          )
      )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
    new {
        object Version {
            val akkaHttpVersion = "10.0.11"
            val argonautVersion = "6.2"
            val circeVersion = "0.8.0"
            val jacksonScalaVersion = "2.9.1"
            val json4sVersion = "3.6.0-M2"
            val playVersion = "2.6.5"
            val scalaTestVersion = "3.0.4"
            val upickleVersion = "0.4.4"
            val avro4sVersion = "1.8.2-SNAPSHOT"
            val catsVersion = "1.8.2-SNAPSHOT"
        }
        val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttpVersion
        val akkaHttpJacksonJava = "com.typesafe.akka" %% "akka-http-jackson" % Version.akkaHttpVersion
        val argonaut = "io.argonaut" %% "argonaut" % Version.argonautVersion
        val circeCore = "io.circe" %% "circe-core" % Version.circeVersion
        val circeJawn = "io.circe" %% "circe-jawn" % Version.circeVersion
        val circeGeneric = "io.circe" %% "circe-generic" % Version.circeVersion
        val circeJava8 = "io.circe" %% "circe-java8" % Version.circeVersion
        val circeParser = "io.circe" %% "circe-parser" % Version.circeVersion
        val jacksonScala = "com.fasterxml.jackson.module" %% "jackson-module-scala" % Version.jacksonScalaVersion
        val json4sCore = "org.json4s" %% "json4s-core" % Version.json4sVersion
        val json4sJackson = "org.json4s" %% "json4s-jackson" % Version.json4sVersion
        val json4sNative = "org.json4s" %% "json4s-native" % Version.json4sVersion
        val playJson = "com.typesafe.play" %% "play-json" % Version.playVersion
        val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTestVersion
        val upickle = "com.lihaoyi" %% "upickle" % Version.upickleVersion
        val avro4sCore = "com.sksamuel.avro4s" %% "avro4s-core" % Version.avro4sVersion
        val avro4sJson = "com.sksamuel.avro4s" %% "avro4s-json" % Version.avro4sVersion
    }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
    commonSettings ++
      gitSettings ++
      scalafmtSettings ++
      publishSettings

lazy val commonSettings =
    Seq(
        // scalaVersion from .travis.yml via sbt-travisci
        version := "1.18.2-SNAPSHOT",
        scalaVersion := "2.12.4",
        organization := "de.heikoseeberger",
        organizationName := "Heiko Seeberger",
        startYear := Some(2015),
        licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
        scalacOptions ++= Seq(
            "-unchecked",
            "-deprecation",
            "-language:_",
            "-target:jvm-1.8",
            "-encoding", "UTF-8"
        ),
        unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
        unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value)
    )

lazy val gitSettings =
    Seq(
        git.useGitDescribe := true
    )

lazy val scalafmtSettings =
    Seq(
        scalafmtOnCompile := true,
        scalafmtOnCompile.in(Sbt) := false,
        scalafmtVersion := "1.4.0"
    )

lazy val publishSettings =
    Seq(
        homepage := Some(url("https://github.com/hseeberger/akka-http-json")),
        scmInfo := Some(ScmInfo(url("https://github.com/hseeberger/akka-http-json"),
            "git@github.com:hseeberger/akka-http-json.git")),
        developers += Developer("hseeberger",
            "Heiko Seeberger",
            "mail@heikoseeberger.de",
            url("https://github.com/hseeberger")),
        //pomIncludeRepository := (_ => false),
        publishTo := {
            val corporateRepo = "http://toucan.simplesys.lan/"
            if (isSnapshot.value)
                Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
            else
                Some("releases" at corporateRepo + "artifactory/libs-release-local")
        },
        credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
        //bintrayPackage := "akka-http-json"
    )
