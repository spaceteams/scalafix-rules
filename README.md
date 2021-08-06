# Scalafix rules

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
You can use this rule by executing `scalafix github:alexanderkogan/scalafix-rules/AllowVariableCases` in the sbt shell.

Configure it by setting `AllowVariableCases.allowedCases = [...]` in your `.scalafix.conf`. For example: `AllowVariableCases.allowedCases = ["snake_case"]`