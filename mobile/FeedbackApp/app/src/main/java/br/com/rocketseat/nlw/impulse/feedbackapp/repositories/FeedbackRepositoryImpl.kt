package br.com.rocketseat.nlw.impulse.feedbackapp.repositories

import br.com.rocketseat.nlw.impulse.feedbackapp.models.Feedback
import br.com.rocketseat.nlw.impulse.feedbackapp.services.http.FeedbackApiEndPoint
import retrofit2.Response

class FeedbackRepositoryImpl(private val feedbackApi: FeedbackApiEndPoint): FeedbackRepository {

    override suspend fun sendFeedback(feedback: Feedback): Response<Feedback> = feedbackApi.postFeedback(feedback)
}