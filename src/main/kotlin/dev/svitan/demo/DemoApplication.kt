package dev.svitan.demo

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    Database.connect("jdbc:postgresql://localhost:5432/demo", driver = "org.postgresql.Driver",
        user = "user", password = "password")
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(ArticleTable)
    }
    runApplication<DemoApplication>(*args)
}
