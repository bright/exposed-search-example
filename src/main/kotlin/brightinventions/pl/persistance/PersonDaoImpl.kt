package brightinventions.pl.persistance

import brightinventions.pl.dto.CreatePersonDto
import brightinventions.pl.dto.FoundPersonDto
import brightinventions.pl.exposed.SearchPropertySpecification
import brightinventions.pl.exposed.SearchSpecification
import brightinventions.pl.exposed.search
import brightinventions.pl.persistance.table.PersonTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PersonDaoImpl : PersonDao {

    override fun findAll(): List<FoundPersonDto> = transaction {
        PersonTable.selectAll().map(::mapToFoundPerson)
    }

    override fun findByQuery(query: String): List<FoundPersonDto> = transaction {
        PersonTable
            .selectAll()
            .search(query, SearchSpecification(listOf(
                SearchPropertySpecification("name", PersonTable.name),
                SearchPropertySpecification("age", PersonTable.age)
            ))).map(::mapToFoundPerson)
    }

    private fun mapToFoundPerson(it: ResultRow) = FoundPersonDto(
        it[PersonTable.id],
        it[PersonTable.name],
        it[PersonTable.surname],
        it[PersonTable.age]
    )

    override fun create(person: CreatePersonDto) {
        transaction {
            PersonTable.insert {
                it[name] = person.name
                it[surname] = person.surname
                it[age] = person.age
            }
        }
    }
}
