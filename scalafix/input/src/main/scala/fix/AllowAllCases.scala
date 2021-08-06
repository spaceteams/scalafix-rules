/*
rule = AllowVariableCases
AllowVariableCases.allowedCases = [camelCase, snake_case, PascalCase, UPPERCASE]
 */
package fix

abstract class AllowAllCases {
  val camelCase: Any
  val lowercase: Any
  val PascalCase: Any
  val UPPER_CASE: Any
  val snake_case: Any

  val mixed_Case: Any /* assert: AllowVariableCases
      ^^^^^^^^^^
  unknown case is not allowed. Allowed cases are [camelCase, snake_case, PascalCase, UPPERCASE].
 */
}
