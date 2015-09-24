package controllers

import models.Entities._
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mock.MockitoSugar
import play.api.test._
import play.api.test.Helpers._

class UserControllerTest extends WordSpec with Matchers with MockitoSugar {

  // TODO: implement by calling user info endpoint
  "unverified users can not log in" in new Fixture {
  }

  // "registration" can {
  //   "create unverified user" in new Fixture {

  //   }
  // }

  // "registration" can {
  //   "be accessed when authenticated" in new Fixture {
  //     val response = controller.userEndpoint()(requestValidAuth)
  //     status(response) shouldBe OK
  //   }

  //   "not be accessed" when {
  //     "not authenticated" in new Fixture {
  //       val response = controller.userEndpoint()(FakeRequest())
  //       status(response) shouldBe UNAUTHORIZED
  //     }

  //     "using invalid credentials" in new Fixture {
  //       val response = controller.userEndpoint()(requestWrongPass)
  //       status(response) shouldBe UNAUTHORIZED
  //     }
  //   }
  // }

  trait Fixture {
    // val requestValidAuth = FakeRequest().withHeaders(
    //   ("Authorization", AuthenticationHelpers.authHeaderValue(Credentials(User("michael"), Password("correct password"))))
    // )
    // val requestWrongPass = FakeRequest().withHeaders(
    //   ("Authorization", AuthenticationHelpers.authHeaderValue(Credentials(User("michael"), Password("wrong password"))))
    // )
    lazy val controller = new UserController {
    }
  }
}
