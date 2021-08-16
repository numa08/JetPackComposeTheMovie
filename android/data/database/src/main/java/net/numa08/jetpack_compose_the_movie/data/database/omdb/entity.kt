package net.numa08.jetpack_compose_the_movie.data.database.omdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poster")
data class Poster(
    @PrimaryKey val titleId: String,
    val poster: String?
)