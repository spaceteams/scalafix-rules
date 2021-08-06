/*
rule = AllowVariableCases
AllowVariableCases.allowedCases = [UPPERCASE]
 */
package fix

abstract class AllowUpperCase {
  val UPPERCASE: Any
  val UPPER_SNAKE_CASE: Any
  val WITH1NUMBER: Any

  val PascalCase: Any // assert: AllowVariableCases
  val snake_case: Any // assert: AllowVariableCases
  val mixed_Case: Any // assert: AllowVariableCases

  val camelCase: Any /* assert: AllowVariableCases
      ^^^^^^^^^
  camelCase is not allowed. Allowed cases are [UPPERCASE].
 */
}
