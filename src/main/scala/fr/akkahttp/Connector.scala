package fr.akkahttp

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{HttpApp, Route}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

/**
  * Created by eliottlambert on 10/06/2017.
  */
object Connector extends HttpApp {

  def main(args: Array[String]): Unit = {
    startServer("localhost", 8080)
  }

  override protected def routes: Route = pathPrefix("bank") {
    post {
      pathPrefix("accounts" / "create") {
        entity(as[Account]) { accountData =>
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>Say hello to akka-http $accountData</h1>"))
        }
      }
    } ~
      get {
        pathPrefix("accounts" / Segment) { iban =>
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>Say hello to akka-http $iban</h1>"))
        }
      }
  }

}

case class Account(mail: String, name: String) {
  require(name.isEmpty, "name must not be empty")
  require(!mail.isEmpty, "mail must not be empty")
  require(isValid(mail), "mail must be valid")

  def isValid(email: String): Boolean = """(?=[^\s]+)(?=(\w+)@([\w\.]+))""".r.findFirstIn(email).isDefined
}
