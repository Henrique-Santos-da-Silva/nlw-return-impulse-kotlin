package br.com.rocketseat.nlw.impulse.feedbackapp.repositories

import br.com.rocketseat.nlw.impulse.feedbackapp.models.Feedback
import retrofit2.Response

interface FeedbackRepository {

    suspend fun sendFeedback(feedback: Feedback): Response<Feedback>
}