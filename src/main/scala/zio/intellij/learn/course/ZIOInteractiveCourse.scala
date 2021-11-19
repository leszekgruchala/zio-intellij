package zio.intellij.learn.course

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.wm.impl.welcomeScreen.learnIde.{HeightLimitedPane, LearnIdeContentColorsAndFonts}
import com.intellij.openapi.wm.{InteractiveCourseData, InteractiveCourseFactory}
import com.intellij.ui.components.labels.LinkLabel
import com.intellij.ui.scale.JBUIScale
import training.learn.CourseManager
import training.learn.course.IftModule
import training.ui.views.NewContentLabel
import training.util.UtilsKt
import zio.intellij.ZioIcon

import java.awt.Component
import java.awt.event.ActionEvent
import javax.swing._
import javax.swing.plaf.{FontUIResource, LabelUI}
import scala.jdk.CollectionConverters.CollectionHasAsScala

final class ZIOInteractiveCourse extends InteractiveCourseFactory {
  override def getInteractiveCourseData: InteractiveCourseData = new ZIOInteractiveCourseData()

  private class ZIOInteractiveCourseData() extends InteractiveCourseData {
    override def getName: String = "Learn ZIO"

    override def getDescription: String = "Interactive tutorial designed to help you learn ZIO"

    override def getIcon: Icon = ZioIcon

    override def getActionButtonName(): String = "Start Learning"

    override def getAction(): Action =
      new AbstractAction("Start Learning") {
        override def actionPerformed(e: ActionEvent): Unit = ()
      }

    override def getExpandContent(): JComponent = {
      val modules = ApplicationManager.getApplication().getService(classOf[CourseManager]).getModules.asScala.toList

      val panel = new JPanel()
      panel.setOpaque(false)
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS))

      panel.add(UtilsKt.rigid(16, 1))
      modules.foreach { module =>
        val moduleHeader = getModuleHeader(module)
        //      if (!moduleHasNewContent(module)) {
        //      panel.add(moduleHeader)
        //    } else {
        val nameLine = new JPanel()
        nameLine.setOpaque(false)
        nameLine.setLayout(new BoxLayout(nameLine, BoxLayout.X_AXIS))
        nameLine.setAlignmentX(Component.LEFT_ALIGNMENT)

        nameLine.add(moduleHeader)
        nameLine.add(UtilsKt.rigid(10, 0))
        nameLine.add(new NewContentLabel())

        panel.add(nameLine)
        //      }
        panel.add(UtilsKt.rigid(2, 2))
        panel.add(moduleDescription(module))
        panel.add(UtilsKt.rigid(16, 16))
      }
      panel.add(UtilsKt.rigid(16, 15))

      panel
    }

    private def moduleDescription(module: IftModule): HeightLimitedPane =
      new HeightLimitedPane(
        module.getDescription,
        -1,
        LearnIdeContentColorsAndFonts.INSTANCE.getModuleDescriptionColor,
        false,
        null
      )

    private def getModuleHeader(module: IftModule): LinkLabel[Any] = {
      val linkLabel = new LinkLabel[Any](module.getName, null) {
        override def setUI(ui: LabelUI) = {
          super.setUI(ui)
          if (getFont != null) {
            setFont(new FontUIResource(getFont.deriveFont(
              getFont.getSize2D + JBUIScale.scale(-1) + (if (SystemInfo.isWindows) JBUIScale.scale(1) else 0))))
          }
        }
      }
      linkLabel.setName(s"linkLabel.${module.getName}")
      linkLabel
    }


    override def newContentMarker(): JComponent = null
  }
}
