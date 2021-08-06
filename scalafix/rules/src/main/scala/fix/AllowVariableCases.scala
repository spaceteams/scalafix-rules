package fix

import metaconfig.ConfDecoder
import metaconfig.Configured
import metaconfig.generic.Surface
import scalafix.v1._

import scala.meta._

class AllowVariableCases(config: AllowedCasesConfig) extends SyntacticRule("AllowVariableCases") {

  def this() = this(AllowedCasesConfig.default)

  override def withConfiguration(config: Configuration): Configured[Rule] =
    config.conf
      .getOrElse("AllowVariableCases")(this.config)
      .map(newConfig => new AllowVariableCases(newConfig))

  override def fix(implicit doc: SyntacticDocument): Patch = matchTree(doc.tree)

  def matchTree(tree: Tree): Patch =
    tree.collect {
      case Defn.Val(_, List(Pat.Var(term)), _, _) => matchTerm(term)
      case Defn.Var(_, List(Pat.Var(term)), _, _) => matchTerm(term)
      case Decl.Val(_, List(Pat.Var(term)), _) => matchTerm(term)
      case Decl.Var(_, List(Pat.Var(term)), _) => matchTerm(term)
    }.asPatch

  def matchTerm(term: Term): Patch =
    term match {
      case Term.Name(name) if (config.isDisallowedCase(name)) => Patch.lint(DisallowedCase(term.pos, config.getCase(name), config.allowedCases))
      case _ => Patch.empty
    }

}

case class DisallowedCase(position: Position, actualCase: String, allowedCases: List[String]) extends Diagnostic {
  override def message: String = s"$actualCase is not allowed. Allowed cases are [${allowedCases.mkString(", ")}]."

  //  override def equals(that: Any): Boolean = ???
}

case class AllowedCasesConfig(allowedCases: List[String] = List("camelCase")) {
  val lowercase = "lowercase"
  val camelCase = "camelCase"
  val snakeCase = "snake_case"
  val pascalCase = "PascalCase"
  val upperCase = "UPPERCASE"
  val unknownCase = "unknown case"

  def isDisallowedCase(name: String): Boolean =
    getCase(name) match {
      case `lowercase` => !(allowedCases.contains(camelCase) || allowedCases.contains(snakeCase))
      case actualCase => !allowedCases.contains(actualCase)
    }

  def getCase(text: String): String = {
    val twoUpperCaseLettersFollowing = "[A-Z]{2}".r findFirstMatchIn text
    val containsUnderscore = "_".r findFirstMatchIn text
    val containsUpperCaseLetters = "[A-Z].".r findFirstMatchIn text
    val containsLowerCaseLetters = "[a-z].".r findFirstMatchIn text
    val containsOnlyLowerCaseLetters = "^[a-z]*$".r findFirstMatchIn text
    val firstLetterUpperCase = "^[A-Z].".r findFirstMatchIn text

    val isLowerCase = containsOnlyLowerCaseLetters.isDefined
    val isCamelCase = firstLetterUpperCase.isEmpty && twoUpperCaseLettersFollowing.isEmpty && containsUnderscore.isEmpty
    val isSnakeCase = (containsUpperCaseLetters.isEmpty && containsUnderscore.isDefined)
    val isPascalCase = firstLetterUpperCase.isDefined && twoUpperCaseLettersFollowing.isEmpty && containsUnderscore.isEmpty
    val isUpperCase = containsLowerCaseLetters.isEmpty

    if (isLowerCase) lowercase // This has to be first, because it's a special case. For example, "word" is both valid in camel and snake case.
    else if (isCamelCase) camelCase
    else if (isSnakeCase) snakeCase
    else if (isPascalCase) pascalCase
    else if (isUpperCase) upperCase
    else unknownCase
  }
}

object AllowedCasesConfig {
  val default: AllowedCasesConfig = AllowedCasesConfig()
  implicit val surface: Surface[AllowedCasesConfig] = metaconfig.generic.deriveSurface[AllowedCasesConfig]
  implicit val decoder: ConfDecoder[AllowedCasesConfig] = metaconfig.generic.deriveDecoder(default)
}