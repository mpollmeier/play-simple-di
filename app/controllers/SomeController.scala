package controllers

import javax.inject._
import models.Entities._
import org.slf4j.LoggerFactory
import play.api.mvc._

class Hello {
  def sayHello(name: String) = "Hello " + name
}

class SomeController @Inject() (hello: Hello) extends Controller {
  protected val log = LoggerFactory.getLogger(getClass.getSimpleName)

  def someEndpoint() = Authenticated { req ⇒
    println("inside endpoint")
    Ok(hello.sayHello("michael"))
  }
}
