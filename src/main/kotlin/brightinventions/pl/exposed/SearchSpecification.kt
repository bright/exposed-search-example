package brightinventions.pl.exposed

import cz.jirutka.rsql.parser.ast.ComparisonOperator
import org.jetbrains.exposed.sql.Column

data class SearchSpecification(
    val properties: List<SearchPropertySpecification>
)

data class SearchPropertySpecification(
    val operators: List<ComparisonOperator>,
    val name: String,
    val column: Column<*>
)
