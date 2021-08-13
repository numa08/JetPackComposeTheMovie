package net.numa08.jetpack_compose_the_movie.presentation

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import net.numa08.jetpack_compose_the_movie.feature.worker.InsertInitialDataWorker
import javax.inject.Inject

@HiltAndroidApp
class JetpackComposeTheMovieApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        val insertWorker = OneTimeWorkRequest.from(InsertInitialDataWorker::class.java)
        WorkManager.getInstance(this).enqueue(insertWorker)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}