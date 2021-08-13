package net.numa08.jetpack_compose_the_movie.feature.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import net.numa08.jetpack_compose_the_movie.data.*
import net.numa08.jetpack_compose_the_movie.data.database.imdb.*
import net.numa08.jetpack_compose_the_movie.data.json.JsonType
import net.numa08.jetpack_compose_the_movie.domain.repository.ApplicationStateRepository

@Suppress("BlockingMethodInNonBlockingContext")
@HiltWorker
class InsertInitialDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val titleDao: IMDBDao,
    private val applicationStateRepository: ApplicationStateRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val currentState = runBlocking { applicationStateRepository.state.first() }
        val isInitialDataInserted = currentState.isInitialDataInserted
        if (isInitialDataInserted) {
            return Result.success(workDataOf("result" to "noop"))
        }
        return coroutineScope {
            val loadGenreTask = async { loadGenre() }
            val loadTitleTask = async {
                loadOriginalTitle()
                loadTitle()
            }
            listOf(loadGenreTask, loadTitleTask).awaitAll()
            loadGenreTitle()
            applicationStateRepository
                .updateApplicationState(
                    currentState.copy(
                        isInitialDataInserted = true
                    )
                )

            return@coroutineScope Result.success(workDataOf("result" to "inserted"))
        }
    }

    private suspend fun loadGenreTitle() = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        applicationContext
            .assets
            .open("genre_title.jsonl").reader().useLines {
                it.forEach { line ->
                    val genre = JsonType.fromJsonString<JsonType.GenreTitle>(line)
                    val genreTitle = GenreTitleEntity(genre = genre.genre, titleId = genre.titleId)
                    titleDao.insertGenreTitle(genreTitle)
                }
            }
    }

    private suspend fun loadGenre() = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        applicationContext
            .assets
            .open("genre.jsonl").reader().useLines {
                it.forEach { line ->
                    val genre = JsonType.fromJsonString<JsonType.Genre>(line)
                    val genreMaster =
                        GenreMasterEntity(genre = genre.genre, jaGenre = genre.ja)
                    titleDao.insertGenre(genreMaster)
                }
            }
    }

    private suspend fun loadOriginalTitle() = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        applicationContext
            .assets
            .open("title.basic.jsonl")
            .reader()
            .useLines {
                it.forEach { line ->
                    val titleBasic = JsonType.fromJsonString<JsonType.TitleBasic>(line)
                    val originalTitle = OriginalTitleEntity(
                        titleId = titleBasic.tconst,
                        primaryTitle = titleBasic.primaryTitle,
                        originalTitle = titleBasic.originalTitle,
                        isAdult = titleBasic.isAdult,
                        startYear = titleBasic.startYear?.toInt(),
                        endYear = titleBasic.endYear?.toInt(),
                        runtimeMinutes = titleBasic.runtimeMinutes?.toInt(),
                        titleType = titleBasic.titleType
                    )
                    titleDao.insertOriginalTitle(originalTitle)
                }
            }
    }

    private suspend fun loadTitle() = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        applicationContext
            .assets
            .open("title.aka.jsonl")
            .reader()
            .useLines {
                it.forEach { line ->
                    val titleAka = JsonType.fromJsonString<JsonType.TitleAka>(line)
                    val title = TitleEntity(
                        titleId = titleAka.titleId,
                        title = titleAka.title,
                        language = titleAka.language,
                        region = titleAka.region,
                        isOriginalTitle = titleAka.isOriginalTitle
                    )
                    titleDao.insertTitle(title)
                }
            }
    }
}