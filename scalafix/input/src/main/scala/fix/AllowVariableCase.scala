/*
rule = AllowVariableCases
 */
package fix

abstract class AllowVariableCase {
  val valTypeAssignment: String = "text"
  val valTypeNoAssignment: Boolean
  val valNoTypeAssignment = 1

  var varTypeAssignment: String = "text"
  var varTypeNoAssignment: Boolean
  var varNoTypeAssignment = Math.abs(-1)
}
