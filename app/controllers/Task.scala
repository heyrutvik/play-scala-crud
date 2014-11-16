package controllers

import  play.api._
import  play.api.mvc._
import  models.{Task => TaskM}
import  play.api.data._
import  play.api.data.Forms._

object Task extends Controller {

    def tasks = Action {
        Ok(views.html.tasks(TaskM.fetchAll, form, true))
    }

    def create = Action { implicit request =>
        form.bindFromRequest.fold(
            hasErrors = { form =>
                println("error")
                Redirect(routes.Task.tasks)
            },
            success = { task => 
                println("create: " + task)
                TaskM.create(task)
                Redirect(routes.Task.tasks)
            }
        )
    }

    def read(id: Long) = Action {
        Ok(views.html.detail(TaskM.fetchById(id)))
    }

    def edit(id: Long) = Action {
        Ok( 
            views.html.tasks(TaskM.fetchAll, 
            form.fill(TaskM.fetchById(id)),
            false)
        )
    }

    def update(id: Long) = Action { implicit request =>
        form.bindFromRequest.fold(
            hasErrors = { form =>
                println("error")
                Redirect(routes.Task.tasks)
            },
            success = { task => 
                println("update: " + task)
                TaskM.update(task)
                Redirect(routes.Task.tasks)
            }
        )
    }

    def delete(id: Long) = Action {
        TaskM.delete(id)
        Redirect(routes.Task.tasks)
    }

    var form: Form[TaskM] = Form(
        mapping(
            "id" -> longNumber,
            "label" -> text,
            "desc" -> text
        )(TaskM.apply)(TaskM.unapply)
    )

}
