/*
rule = AllowVariableCases
AllowVariableCases.allowedCases = [PascalCase]
 */
package fix

abstract class AllowPascalCase {
  val PascalCase: Any
  val Word: Any
  val With1Number: Any

  val camelCase: Any // assert: AllowVariableCases
  val snake_case: Any // assert: AllowVariableCases
  val mixed_Case: Any // assert: AllowVariableCases

  val UPPER_CASE: Any /* assert: AllowVariableCases
      ^^^^^^^^^^
  UPPERCASE is not allowed. Allowed cases are [PascalCase].
 */
}
