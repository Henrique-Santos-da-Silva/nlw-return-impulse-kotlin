package server.impulse.com.nextlevelweek.services.sendmail

import server.impulse.com.nextlevelweek.models.FeedbackModel

interface SendMail {
    suspend fun sendMail(feedbackModel: FeedbackModel)
}