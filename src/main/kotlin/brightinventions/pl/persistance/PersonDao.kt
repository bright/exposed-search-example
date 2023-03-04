package brightinventions.pl.persistance

import brightinventions.pl.dto.CreatePersonDto
import brightinventions.pl.dto.FoundPersonDto

interface PersonDao {

    fun findAll(): List<FoundPersonDto>
    fun findByQuery(query: String): List<FoundPersonDto>
    fun create(person: CreatePersonDto)
}
