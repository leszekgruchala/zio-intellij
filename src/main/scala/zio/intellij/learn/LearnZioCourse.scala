package zio.intellij.learn

import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.wm.ToolWindowAnchor
import kotlin.jvm.functions
import training.lang.AbstractLangSupport
import training.learn.course.{IftModule, KLesson, LearningCourse, LearningCourseBase, LessonType}
import zio.intellij.learn.lessons.GettingStarted

import java.util
import scala.jdk.CollectionConverters._

final class LearnZioCourse extends LearningCourseBase("Scala") {
  override def modules(): util.Collection[IftModule] =
    List[IftModule](
      new ZioLearningModule(
        GettingStarted
      )
    ).asJavaCollection

  private class ZioLearningModule(lesson: KLesson, lessons: KLesson*)
      extends IftModule(
        "ZIO",
        "Get started with ZIO",
        "Some description",
        getLangSupport,
        LessonType.SCRATCH,
        () => (lesson :: lessons.toList).asJava
      ) {
    override def getSanitizedName: String = "ZIO"

    override def preferredLearnWindowAnchor(project: Project): ToolWindowAnchor = ToolWindowAnchor.LEFT
  }
}
