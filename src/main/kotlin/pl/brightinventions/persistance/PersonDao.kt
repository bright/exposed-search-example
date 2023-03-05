package pl.brightinventions.persistance

import pl.brightinventions.dto.CreatePersonDto
import pl.brightinventions.dto.FoundPersonDto

interface PersonDao {

    fun findAll(): List<FoundPersonDto>
    fun findByQuery(query: String): List<FoundPersonDto>
    fun create(person: CreatePersonDto)
}
