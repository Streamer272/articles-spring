package dev.svitan.demo

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/articles")
class ArticlesController {
    @GetMapping("/")
    fun all(): List<ArticleDTO> {
        return transaction {
            Article.all().map { it.toDTO() }
        }
    }

    @GetMapping("/{id}")
    fun id(@PathVariable("id") id: String): ArticleDTO? {
        return transaction {
            Article.findById(UUID.fromString(id))?.toDTO()
        }
    }

    @PostMapping("/")
    fun create(@RequestBody article: ArticleInDTO) {
        return transaction {
            ArticleTable.insert {
                it[title] = article.title
                it[body] = article.body
            }
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String) {
        return transaction {
            ArticleTable.deleteWhere { ArticleTable.id eq UUID.fromString(id) }
        }
    }
}
