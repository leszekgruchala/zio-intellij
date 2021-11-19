package zio.intellij.learn.lessons

object GettingStarted extends ZioLesson("ZIO.GettingStarted", "Get started with ZIO") {

  override def getLessonContent =
    ctx => {
      import ctx._

      task { tc =>
        text("In this lesson we'll learn how to say hello using ZIO and so <strong>much more</strong>!")
        kotlin.Unit.INSTANCE
      }

      kotlin.Unit.INSTANCE
    }
}
