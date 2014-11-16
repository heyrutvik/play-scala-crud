package models

import  anorm._
import  anorm.SqlParser._

import  play.api.db._
import  play.api.Play.current

case class Task(id: Long = 0, label: String = "", desc: String = "")

object Task {

    var task = {
        get[Long]("id") ~ 
        get[String]("label") ~
        get[String]("desc") map {
            case id~label~desc => Task(id, label, desc)
        }
    }

    def fetchAll: List[Task] = DB.withConnection { implicit c => 
        SQL("select * from curd order by `id` desc").as(task *)
    }

    def fetchById(id: Long) = DB.withConnection { implicit c =>
        SQL("select * from curd where `id` = {id}")
            .on("id"->id)
            .as(task.single)
    }

    def create(task: Task) = DB.withConnection { implicit c =>
        SQL("insert into curd (`label`, `desc`) values ({label}, {desc})")
            .on("label"->task.label, "desc"->task.desc)
            .executeUpdate()
    }

    def update(task: Task) = DB.withConnection { implicit c =>
        SQL("update curd " + 
            "set `label` = {label}, `desc` = {desc} " + 
            "where `id` = {id}")
                .on("id"->task.id, "label"->task.label, "desc"->task.desc)
                .executeUpdate()
    }

    def delete(id: Long) = DB.withConnection { implicit c =>
        SQL("delete from curd where `id` = {id}")
            .on("id"->id)
            .executeUpdate()
    }

}
