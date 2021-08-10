package net.numa08.jetpack_compose_the_movie.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import java.io.InputStream
import java.io.OutputStream
import net.numa08.jetpack_compose_the_movie.data.datastore.ApplicationStateOuterClass.ApplicationState

@Suppress("BlockingMethodInNonBlockingContext")
object ApplicationStateSerializer : Serializer<ApplicationState> {
    override val defaultValue: ApplicationState = ApplicationState.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ApplicationState =
        ApplicationState.parseFrom(input)

    override suspend fun writeTo(
        t: ApplicationState,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.applicationStateDataStore: DataStore<ApplicationState> by dataStore(
    fileName = "ApplicationState.pd",
    serializer = ApplicationStateSerializer
)