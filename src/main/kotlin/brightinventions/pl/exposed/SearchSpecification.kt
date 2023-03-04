package brightinventions.pl.exposed

import org.jetbrains.exposed.sql.Column

data class SearchSpecification(
    val properties: List<SearchPropertySpecification>
)

data class SearchPropertySpecification(
    val name: String,
    val column: Column<*>
)
