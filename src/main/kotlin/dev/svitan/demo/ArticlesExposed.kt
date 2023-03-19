package dev.svitan.demo

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object ArticleTable : UUIDTable("articles") {
    val title = text("title")
    val body = text("body")
}

class Article(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Article>(ArticleTable)

    val title by ArticleTable.title
    val body by ArticleTable.body

    fun toDTO(): ArticleDTO {
        return ArticleDTO(this.id.toString(), this.title, this.body)
    }
}

data class ArticleDTO(val id: String, val title: String, val body: String)
data class ArticleInDTO(val title: String, val body: String)
