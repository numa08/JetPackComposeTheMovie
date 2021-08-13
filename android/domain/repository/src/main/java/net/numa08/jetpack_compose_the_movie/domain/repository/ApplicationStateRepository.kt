package net.numa08.jetpack_compose_the_movie.domain.repository

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.numa08.jetpack_compose_the_movie.data.datastore.ApplicationStateOuterClass
import net.numa08.jetpack_compose_the_movie.domain.data.app_state.ApplicationState

class ApplicationStateRepository(
    private val dataStore: DataStore<ApplicationStateOuterClass.ApplicationState>
) {

    val state: Flow<ApplicationState> = dataStore
        .data
        .map { ApplicationState(isInitialDataInserted = it.isInitialDataInserted) }

    suspend fun updateApplicationState(applicationState: ApplicationState) {
        dataStore.updateData {
            it.toBuilder()
                .setIsInitialDataInserted(applicationState.isInitialDataInserted)
                .build()
        }
    }

}