@file:Suppress("BlockingMethodInNonBlockingContext")

package net.numa08.jetpack_compose_the_movie.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import java.io.InputStream
import java.io.OutputStream

object ApplicationStateSerializer : Serializer<ApplicationStateOuterClass.ApplicationState> {
    override val defaultValue: ApplicationStateOuterClass.ApplicationState =
        ApplicationStateOuterClass.ApplicationState.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ApplicationStateOuterClass.ApplicationState =
        ApplicationStateOuterClass.ApplicationState.parseFrom(input)

    override suspend fun writeTo(
        t: ApplicationStateOuterClass.ApplicationState,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.applicationStateDataStore: DataStore<ApplicationStateOuterClass.ApplicationState> by dataStore(
    fileName = "ApplicationState.pd",
    serializer = ApplicationStateSerializer
)