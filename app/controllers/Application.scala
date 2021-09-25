package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current

import play.api.db._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(null))
  }

  def db = Action {
    var out = ""
    val conn = DB.getConnection()
    try {
      val stmt = conn.createStatement

      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS mates (numero INT)")
      stmt.executeUpdate("INSERT INTO mates VALUES (3)")

      val rs = stmt.executeQuery("SELECT numero FROM mates")

      while (rs.next) {
        out += "Read from DB: " + rs.getInt("numero") + "\n"
      }
    } finally {
      conn.close()
    }
    Ok(out)
  }
}
