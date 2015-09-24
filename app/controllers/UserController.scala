package controllers

import models.Entities._
import org.slf4j.LoggerFactory
import play.api.mvc._

object UserController extends UserController

trait UserController extends Controller {
  protected val log = LoggerFactory.getLogger(getClass.getSimpleName)

  def details = Authenticated { req =>
    println("inside endpoint")
    println(req.user)
    Ok
  }

  // def registerUser(user: User) = Authenticated { req =>
  //   println("inside endpoint")
  //   println(req.user)
  //   Ok
  // }
}
