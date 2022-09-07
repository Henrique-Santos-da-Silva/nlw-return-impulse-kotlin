package br.com.rocketseat.nlw.impulse.feedbackapp

import android.app.Application
import br.com.rocketseat.nlw.impulse.feedbackapp.di.feedbackModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FeedbackApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FeedbackApplication)
            modules(feedbackModule)
        }
    }
}