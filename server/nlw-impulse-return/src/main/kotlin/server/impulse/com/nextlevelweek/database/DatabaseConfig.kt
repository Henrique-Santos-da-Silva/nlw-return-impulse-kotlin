package server.impulse.com.nextlevelweek.database

import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import server.impulse.com.nextlevelweek.utils.dbQuery
import java.io.File

object DatabaseConfig {

    fun openDatabaseConnection(config: ApplicationConfig) {
//        val databaseDriver: String = config.property("storage.sqliteDriver").getString()
//        val jdbcUrl = config.property("storage.jdbcUrl").getString() + (config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
//            File(it).canonicalFile.absoluteFile
//        } ?: "")
//        Database.connect("jdbc:sqlite:$DATABASE_PATH", "org.sqlite.JDBC")

        val databaseDriver = config.property("storage.postgresDriver").getString()
        val jdbcUrl = config.property("storage.jdbcUrl").getString()

        Database.connect(url = jdbcUrl, driver = databaseDriver)
    }

    suspend fun createSchemas() {
        dbQuery { SchemaUtils.create(Feedback) }
    }
}