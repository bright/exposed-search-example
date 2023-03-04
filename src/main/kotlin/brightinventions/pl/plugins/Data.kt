package brightinventions.pl.plugins

import brightinventions.pl.dto.CreatePersonDto
import brightinventions.pl.exposed.Database
import brightinventions.pl.persistance.PersonDaoImpl
import brightinventions.pl.persistance.table.PersonTable
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureData() {
    Database.register()
    transaction {
        SchemaUtils.create(PersonTable)
        PersonDaoImpl().create(CreatePersonDto("John", "Doe", 33))
        PersonDaoImpl().create(CreatePersonDto("George", "Smith", 34))
        PersonDaoImpl().create(CreatePersonDto("Megan", "Miller", 22))
    }
}
