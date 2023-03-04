package brightinventions.pl.exposed

import cz.jirutka.rsql.parser.ast.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greater
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.less
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.notInList
import java.time.Instant
import java.time.format.DateTimeParseException

class ExposedRSQLVisitor(
    private val searchSpecification: SearchSpecification
) : NoArgRSQLVisitorAdapter<Op<Boolean>>() {

    override fun visit(node: AndNode): Op<Boolean> {
        TODO("Not yet implemented")
    }

    override fun visit(node: OrNode): Op<Boolean> {
        TODO("Not yet implemented")
    }

    @Suppress("UNCHECKED_CAST")
    override fun visit(node: ComparisonNode): Op<Boolean> {
        val arguments =
            node.arguments.map {
                it.toLongOrNull()
                    ?: it.toBooleanStrictOrNull()
                    ?: it.toDateOrNull()
                    ?: it.toDoubleOrNull()
                    ?: it
            }
        val argument = arguments.first()

        val property = searchSpecification.properties.first { it.name == node.selector }
        val column = property.column as Column<Any>
        return when (val operator = node.operator) {
            RSQLOperators.EQUAL -> column eq argument
            RSQLOperators.NOT_EQUAL -> column neq argument
            RSQLOperators.GREATER_THAN -> column greater argument as Comparable<Any>
            RSQLOperators.GREATER_THAN_OR_EQUAL -> column greaterEq argument as Comparable<Any>
            RSQLOperators.LESS_THAN -> column less argument as Comparable<Any>
            RSQLOperators.LESS_THAN_OR_EQUAL -> column lessEq argument as Comparable<Any>
            RSQLOperators.IN -> column inList arguments
            RSQLOperators.NOT_IN -> column notInList arguments

            else -> throw Exception("Filter operator '$operator' not supported")
        }
    }
}

private fun String.toDateOrNull(): Instant? = try {
    Instant.parse(this)
} catch (e: DateTimeParseException) {
    null
}
