package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

    def index = Action {
        // redirect to list of tasks
        Redirect(routes.Task.tasks)
    }

}
