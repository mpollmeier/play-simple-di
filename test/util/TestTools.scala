package util

import scala.annotation.tailrec
import scala.concurrent.duration._
import scala.util.control.NoStackTrace
import org.scalatest._
import org.scalatestplus.play._
import play.api.test.FakeRequest
import controllers.AuthenticationHelpers
import models.Entities._

abstract class MyPlaySpec extends WordSpec with Matchers with OptionValues with WsScalaTestClient

object TestTools {
  implicit class PimpedRequest[A](request: FakeRequest[A]) {
    def withValidAuth() = request.withHeaders(
      ("Authorization", AuthenticationHelpers.authHeaderValue(Credentials(User("michael"), Password("correct password"))))
    )

    def withInvalidAuth() = request.withHeaders(
      ("Authorization", AuthenticationHelpers.authHeaderValue(Credentials(User("michael"), Password("wrong password"))))
    )
  }

  case class AwaitConditionFailedException(message: String, e: Throwable)
      extends RuntimeException(s"$message: [${e.getMessage}]") with NoStackTrace {
    this.setStackTrace(e.getStackTrace)
  }

  def awaitCondition[T](message: String, max: Duration = 10 seconds, interval: Duration = 70 millis)(predicate: ⇒ T) {
    def now: FiniteDuration = System.nanoTime.nanos
    val stop = now + max

    @tailrec
    def poll(nextSleepInterval: Duration) {
      try predicate
      catch {
        case e: Throwable ⇒
          if (now > stop)
            throw new AwaitConditionFailedException(message, e)
          else {
            Thread.sleep(nextSleepInterval.toMillis)
            poll((stop - now) min interval)
          }
      }
    }
    poll(max min interval)
  }
}
