package zio.intellij.learn

import com.intellij.icons.AllIcons
import com.intellij.openapi.Disposable
import com.intellij.openapi.wm.impl.welcomeScreen.TabbedWelcomeScreen.DefaultWelcomeScreenTab
import com.intellij.openapi.wm.{WelcomeScreenTab, WelcomeTabFactory}
import com.intellij.ui.components.JBLabel
import zio.intellij.ZioIcon
import zio.intellij.learn.ui.LearnZioContentPanel

import java.util
import javax.swing.{JComponent, JPanel}
import scala.jdk.CollectionConverters._

class LearnZioWelcomeTab {}

final class LearnZioWelcomeTabFactory extends WelcomeTabFactory {
  override def createWelcomeTab(parentDisposable: Disposable): WelcomeScreenTab =
    new LearnZioTab(parentDisposable)

  private final class LearnZioTab(parentDisposable: Disposable)
      extends DefaultWelcomeScreenTab("Get started with ZIO") {

    myKeyComponent.add(new JBLabel(AllIcons.General.Beta))

    override def buildComponent(): JComponent = new LearnZioContentPanel(parentDisposable)

    override def getChildTabs: util.List[WelcomeScreenTab] =
      List[WelcomeScreenTab](new DefaultWelcomeScreenTab("Learn ZIO", ZioIcon) {
        override def buildComponent(): JComponent = new JPanel
      }).asJava
  }
}
