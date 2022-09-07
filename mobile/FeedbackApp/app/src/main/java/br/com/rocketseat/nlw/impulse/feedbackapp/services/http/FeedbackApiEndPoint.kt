package br.com.rocketseat.nlw.impulse.feedbackapp.services.http

import br.com.rocketseat.nlw.impulse.feedbackapp.models.Feedback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FeedbackApiEndPoint {

    @POST("feedbacks")
    suspend fun postFeedback(@Body feedback: Feedback): Response<Feedback>
}