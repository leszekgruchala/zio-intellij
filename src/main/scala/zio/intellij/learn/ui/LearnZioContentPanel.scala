package zio.intellij.learn.ui

import com.intellij.openapi.Disposable
import com.intellij.openapi.wm.impl.welcomeScreen.WelcomeScreenUIManager
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.ui.JBUI
import zio.intellij.learn.ui.LearnZioContentPanel.unscalable24px

import java.awt.{BorderLayout, Rectangle}
import javax.swing.{Box, BoxLayout, JPanel, ScrollPaneConstants}

final class LearnZioContentPanel(private val parentDisposable: Disposable) extends JPanel {

  private val contentPanel: JPanel          = new JPanel()
  private val helpAndResourcesPanel: JPanel = new JPanel()
  private val viewComponent: JPanel = new JPanel() {
    setLayout(new BorderLayout())
    setBackground {
      WelcomeScreenUIManager.getProjectsBackground
    }
  }
  private val myScrollPane: JBScrollPane = new JBScrollPane(
    viewComponent,
    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
  ) { setBorder(JBUI.Borders.empty()) }

  setLayout(new BorderLayout())
  setFocusable(true)
  setOpaque(true)
  setBackground(WelcomeScreenUIManager.getProjectsBackground)

  contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS))
  contentPanel.setBorder(JBUI.Borders.empty(unscalable24px))
  contentPanel.setOpaque(false)
//      initInteractiveCoursesPanel()
//      initHelpAndResourcePanel()
//    contentPanel.add(helpAndResourcesPanel)
  contentPanel.add(Box.createVerticalGlue())
  viewComponent.add(contentPanel, BorderLayout.CENTER)

  add(myScrollPane, BorderLayout.CENTER)
  contentPanel.setBounds(new Rectangle(contentPanel.getLocation(), contentPanel.getPreferredSize()))
  revalidate()
  repaint()
}
object LearnZioContentPanel {
  val unscalable24px = 24
}
