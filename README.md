# Scalafix rules
[![Developed during Spacetime at Spaceteams](https://raw.githubusercontent.com/spaceteams/badges/main/developed-during-spacetime.svg)](https://spaceteams.de)

## Allow Variable Cases
This rule checks, if variables are named in one of the cases that you have defined in the config.

AllowVariableCases.allowedCases can be defined as an array containing:
* `camelCase`
* `snake_case`
* `PascalCase`
* `UPPERCASE` (also allows underscores like `UPPER_CASE`)

Default is `[camelCase]`.

You can define multiple cases, that are allowed. For example `[camelCase, PascalCase]`.

`lowercase` is also recognized, but is allowed with camelCase or snake_case.

### Usage
Configure the rule by setting `AllowVariableCases.allowedCases = [...]` in your `.scalafix.conf`. For example: `AllowVariableCases.allowedCases = ["snake_case"]`

#### Maven Central
You can execute `scalafix dependency:AllowVariableCases@de.spaceteams:scalafix-rules:1.0.0` in the sbt shell.

To install the rule for your build update the scalafixDependencies in your `build.sbt` for example like this
```sbt
ThisBuild / scalafixDependencies +=
  "de.spaceteams" %% "scalafix-rules" % "1.0.0"
```
and execute `scalafix AllowVariableCases` in the sbt shell.

#### GitHub
You can use this rule directly from GitHub by executing `scalafix github:spaceteams/scalafix-rules/AllowVariableCases` in the sbt shell.
