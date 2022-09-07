package server.impulse.com.nextlevelweek.repositories

import server.impulse.com.nextlevelweek.models.FeedbackModel

interface FeedbackRepository {

    suspend fun createFeedback(feedback: FeedbackModel)

    suspend fun getAllFeedbacks(): List<FeedbackModel>
}