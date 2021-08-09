package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "title_aka")
data class Title(
    @PrimaryKey
    val titleId: String,
    val title: String?,
    val language: String?,
    val isOriginalTitle: Boolean
)
