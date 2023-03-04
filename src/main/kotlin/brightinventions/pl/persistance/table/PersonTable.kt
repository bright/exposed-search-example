package brightinventions.pl.persistance.table

import org.jetbrains.exposed.sql.Table

object PersonTable : Table("person") {
    val id = uuid("id").autoGenerate()
    val name = text("name")
    val surname = text("surname")
    val age = integer("age")
}
