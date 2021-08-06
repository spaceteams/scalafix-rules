/*
rule = AllowVariableCases
AllowVariableCases.allowedCases = [camelCase]
 */
package fix

abstract class AllowCamelCase {
  val camelCase: Any
  val lowercase: Any
  val with1Number: Any

  val PascalCase: Any // assert: AllowVariableCases
  val UPPER_CASE: Any // assert: AllowVariableCases
  val mixed_Case: Any // assert: AllowVariableCases

  val snake_case: Any /* assert: AllowVariableCases
      ^^^^^^^^^^
  snake_case is not allowed. Allowed cases are [camelCase].
 */
}
