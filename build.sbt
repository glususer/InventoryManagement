name := "InventoryManagement"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq("org.scalatest"  %% "scalatest"   % "2.2.4" % Test,"mysql" % "mysql-connector-java" % "5.1.18")

val mysql = "mysql" % "mysql-connector-java" % "5.1.12"
    
