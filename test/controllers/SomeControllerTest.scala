package controllers

import models.Entities._
import org.scalatest.{Matchers, WordSpec}
import play.api.inject.bind
import play.api.test._
import play.api.test.Helpers._
import util._
import util.TestTools._

import javax.inject._

class SomeControllerTest extends MyPlaySpec with OneAppPerTestWithOverrides {
  override def overrideModules = Seq(
    bind[Hello].to(
      new Hello {
        override def sayHello(name: String) = "Hallo " + name
      }
    )
  )

  "some endpoint" can {
    "be accessed when authenticated" in {
      val response = route(FakeRequest(GET, "/").withValidAuth).get
      status(response) shouldBe OK
      contentAsString(response) shouldBe "Hallo michael"
    }

    "not be accessed" when {
      "not authenticated" in {
        val response = route(FakeRequest(GET, "/")).get
        status(response) shouldBe UNAUTHORIZED
      }

      "using invalid credentials" in {
        val response = route(FakeRequest(GET, "/").withInvalidAuth).get
        status(response) shouldBe UNAUTHORIZED
      }
    }
  }
}
