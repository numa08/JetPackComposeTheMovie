package net.numa08.jetpack_compose_the_movie.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.numa08.jetpack_compose_the_movie.data.GenreMaster
import net.numa08.jetpack_compose_the_movie.data.OriginalTitle
import net.numa08.jetpack_compose_the_movie.data.Title
import net.numa08.jetpack_compose_the_movie.data.TitleDao
import net.numa08.jetpack_compose_the_movie.data.json.Genre
import net.numa08.jetpack_compose_the_movie.data.json.TitleAka
import net.numa08.jetpack_compose_the_movie.data.json.TitleBasic

@HiltWorker
class InsertInitialDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val titleDao: TitleDao
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return coroutineScope {
            val loadGenreTask = async { loadGenre() }
            val loadTitleTask = async {
                loadOriginalTitle()
                loadTitle()
            }
            listOf(loadGenreTask, loadTitleTask).awaitAll()
            return@coroutineScope Result.success()
        }
    }

    private suspend fun loadGenre() = withContext(Dispatchers.IO) {
        @Suppress("BlockingMethodInNonBlockingContext")
        applicationContext
            .assets
            .open("genre.jsonl").reader().useLines {
                it.forEachIndexed { index, line ->
                    val genre = Json.decodeFromString<Genre>(line)
                    val genreMaster =
                        GenreMaster(id = index, genre = genre.genre, jaGenre = genre.ja)
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
                    val titleBasic = Json.decodeFromString<TitleBasic>(line)
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
                    val titleAka = Json.decodeFromString<TitleAka>(line)
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