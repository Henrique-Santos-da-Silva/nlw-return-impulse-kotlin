package br.com.rocketseat.nlw.impulse.feedbackapp.di

import br.com.rocketseat.nlw.impulse.feedbackapp.contracts.FeedbackApplicationContract
import br.com.rocketseat.nlw.impulse.feedbackapp.presenters.feedback_details.FeedbackDetailsPresenter
import br.com.rocketseat.nlw.impulse.feedbackapp.repositories.FeedbackRepository
import br.com.rocketseat.nlw.impulse.feedbackapp.repositories.FeedbackRepositoryImpl
import br.com.rocketseat.nlw.impulse.feedbackapp.services.http.HttpClient
import br.com.rocketseat.nlw.impulse.feedbackapp.ui.adapters.FeedbackMainCardAdapter
import br.com.rocketseat.nlw.impulse.feedbackapp.ui.details.FeedbackDetailsFragment
import br.com.rocketseat.nlw.impulse.feedbackapp.utils.PixelCopyScreenShotUtils
import org.koin.core.module.Module
import org.koin.dsl.module

val feedbackModule: Module = module {
    factory { FeedbackMainCardAdapter() }
    single { HttpClient.retrofit() }
    single<FeedbackRepository> { FeedbackRepositoryImpl(feedbackApi = get()) }
    single { PixelCopyScreenShotUtils }
    single {FeedbackDetailsFragment()}
    factory { (view: FeedbackApplicationContract.View) -> FeedbackDetailsPresenter(feedbackRepository = get(), screenShotUtils = get(), contractView = view)}
}