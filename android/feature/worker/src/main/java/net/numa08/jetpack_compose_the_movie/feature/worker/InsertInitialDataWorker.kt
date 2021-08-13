package net.numa08.jetpack_compose_the_movie.feature.worker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import net.numa08.jetpack_compose_the_movie.data.*
import net.numa08.jetpack_compose_the_movie.data.database.imdb.*
import net.numa08.jetpack_compose_the_movie.data.datastore.ApplicationStateOuterClass.ApplicationState
import net.numa08.jetpack_compose_the_movie.data.json.JsonType

@Suppress("BlockingMethodInNonBlockingContext")
@HiltWorker
class InsertInitialDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val titleDao: IMDBDao,
    private val applicationStateDataStore: DataStore<ApplicationState>
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val isInitialDataInserted =
            runBlocking { applicationStateDataStore.data.map { it.isInitialDataInserted }.first() }
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
            applicationStateDataStore
                .updateData {
                    it.toBuilder()
                        .setIsInitialDataInserted(true)
                        .build()
                }

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
                    val genreTitle = GenreTitle(genre = genre.genre, titleId = genre.titleId)
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
                        GenreMaster(genre = genre.genre, jaGenre = genre.ja)
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
                    val originalTitle = OriginalTitle(
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
                    val title = Title(
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