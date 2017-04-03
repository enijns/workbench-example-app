package example

import scala.concurrent.Future
import scalajs.concurrent.JSExecutionContext.Implicits.queue
import upickle.default._
import upickle.Js
import autowire._

import outwatch.dom._
import rxscalajs.Observable

object OutWatchApp {
  object ApiFacade {
    def getAll(trigger: Observable[Boolean]): Observable[Seq[Example]] =
      trigger.flatMap(_ => Observable.from(Client[Api].all().call()))

    def save(example: Observable[Example]): Observable[Example] =
      example.flatMap(e => Observable.from(Client[Api].save(e).call()))
  }

  val x = createBoolHandler(true)
  val loadedData = ApiFacade.getAll(x)

  val node = div(
    h1("Hallooo"),
    button(click(true) --> x, "Laad data"),
    ul(
      children <-- loadedData.map(_.map(e => li(em(e.int.toString), e.str)))
    ),
    "joehoe"
  )
}