package server.impulse.com.nextlevelweek

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import server.impulse.com.nextlevelweek.database.DatabaseConfig
import server.impulse.com.nextlevelweek.plugins.*
import server.impulse.com.nextlevelweek.repositories.FeedbackRepositoryImpl
import server.impulse.com.nextlevelweek.services.sendmail.SendMailImpl

fun main(args: Array<String>) {
    embeddedServer(Netty, environment = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load())

        connector {
            port = System.getenv("PORT")?.toInt() ?: 8080
            host = "0.0.0.0"
        }
    }).start(true)
}

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
