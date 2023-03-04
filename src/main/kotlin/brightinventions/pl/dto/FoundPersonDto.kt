package brightinventions.pl.dto

import brightinventions.pl.ktor.CustomUUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class FoundPersonDto(
    @Serializable(with = CustomUUIDSerializer::class)
    val id: UUID,
    val name: String,
    val surname: String,
    val age: Int
)
