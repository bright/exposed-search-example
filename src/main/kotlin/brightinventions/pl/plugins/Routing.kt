package brightinventions.pl.plugins

import brightinventions.pl.persistance.PersonDaoImpl
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    val dao = PersonDaoImpl()

    routing {
        get("/") {
            call.respond(dao.findAll())
        }
        get("/filtered/") {
            call.respond(dao.findByQuery(call.request.queryParameters["query"] ?: ""))
        }
    }
}
