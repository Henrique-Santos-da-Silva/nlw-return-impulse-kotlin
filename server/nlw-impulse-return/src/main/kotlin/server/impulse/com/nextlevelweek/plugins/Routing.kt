package server.impulse.com.nextlevelweek.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.serializer
import server.impulse.com.nextlevelweek.models.FeedbackModel
import server.impulse.com.nextlevelweek.repositories.FeedbackRepository
import server.impulse.com.nextlevelweek.services.sendmail.SendMail
import server.impulse.com.nextlevelweek.utils.jsonFormat

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureRouting(feedbackRepository: FeedbackRepository, sendMail: SendMail) {

    routing {
        get("/feedbacks") {
            val feedbacks: List<FeedbackModel> = feedbackRepository.getAllFeedbacks()

            call.respond(HttpStatusCode.OK, feedbacks)
        }

        post("/feedbacks") {
            val feedback: FeedbackModel = call.receiveOrNull<FeedbackModel>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val serializer = serializer(FeedbackModel::class.java)
            val feedbackJsonFormat: String = jsonFormat.encodeToString(serializer, feedback)

            sendMail.sendMail(feedback)

            feedbackRepository.createFeedback(feedback)

            call.respond(HttpStatusCode.Created, feedbackJsonFormat)
        }
    }
}
