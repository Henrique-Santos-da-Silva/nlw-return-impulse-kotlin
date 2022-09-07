package server.impulse.com.nextlevelweek.database

import org.jetbrains.exposed.sql.Table

object Feedback: Table("feedbacks") {
    val id = uuid("id").autoGenerate()
    val type = varchar("type", 50)
    val comment = varchar("comment", 255)
    val screenshot = varchar("screenshot", 255).nullable()

    override val primaryKey: Table.PrimaryKey = PrimaryKey(id, name = "PK_Feedbacks_Id")
}