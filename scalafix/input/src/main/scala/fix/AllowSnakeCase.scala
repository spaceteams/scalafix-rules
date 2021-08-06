/*
rule = AllowVariableCases
AllowVariableCases.allowedCases = [snake_case]
 */
package fix

abstract class AllowSnakeCase {
  val snake_case: Any
  val word: Any
  val with_1_number: Any

  // wrong cases
  val UPPER_CASE: Any // assert: AllowVariableCases
  val UPPERCASE: Any // assert: AllowVariableCases
  val camelCase: Any // assert: AllowVariableCases
  val mixed_Case: Any // assert: AllowVariableCases

  val PascalCase: Any /* assert: AllowVariableCases
      ^^^^^^^^^^
  PascalCase is not allowed. Allowed cases are [snake_case].
 */
}
