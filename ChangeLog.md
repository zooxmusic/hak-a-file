v0.9.0-M2
=========

+ Added integration with `application` plugin to replace old `OutputSignedJars` task
+ Fixed bug that resulted in duplicate file entries in the resulting Jar
+ Changed plugin id to 'com.github.johnrengelman.shadow' to support Gradle 2.x plugin infrastructure.

v0.0.0-M1
=========

+ Rewrite based on Gradle Jar Task
+ `ShadowJar` now extends `Jar`
+ Removed `signedCompile` and `signedRuntime` configurations in favor of `shadow` configuration
+ Removed `OutputSignedJars` task

<= v0.0.1
=======
