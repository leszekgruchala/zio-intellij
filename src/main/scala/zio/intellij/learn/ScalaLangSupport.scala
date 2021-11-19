package zio.intellij.learn

import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import kotlin.jvm.functions
import training.lang.AbstractLangSupport

class ScalaLangSupport extends AbstractLangSupport {
  override def getPrimaryLanguage: String = "Scala"

  override def applyToProjectAfterConfigure(): functions.Function1[Project, kotlin.Unit] =
    (_:Project) => kotlin.Unit.INSTANCE

  override def checkSdk(sdk: Sdk, project: Project): Unit = ()
}
