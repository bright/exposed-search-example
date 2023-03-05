package pl.brightinventions.exposed

import cz.jirutka.rsql.parser.RSQLParser
import cz.jirutka.rsql.parser.ast.Node
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.transactions.transaction

fun Query.search(query: String, specification: SearchSpecification): Query =
    transaction {
        val rootNode: Node = RSQLParser().parse(query)
        val queryExpression = rootNode.accept(ExposedRSQLVisitor(specification))
        andWhere { queryExpression }
    }
