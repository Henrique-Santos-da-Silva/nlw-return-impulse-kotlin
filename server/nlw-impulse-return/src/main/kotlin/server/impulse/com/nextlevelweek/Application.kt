package server.impulse.com.nextlevelweek

import io.ktor.server.application.*
import kotlinx.coroutines.runBlocking
import server.impulse.com.nextlevelweek.database.DatabaseConfig
import server.impulse.com.nextlevelweek.plugins.*
import server.impulse.com.nextlevelweek.repositories.FeedbackRepositoryImpl
import server.impulse.com.nextlevelweek.services.sendmail.SendMailImpl

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    runBlocking {
        DatabaseConfig.apply {
            openDatabaseConnection(environment.config)
            createSchemas()
        }
    }
    val feedbackRepository = FeedbackRepositoryImpl()
    val sendMail = SendMailImpl()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureRouting(feedbackRepository, sendMail)
}
