package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_master")
data class GenreMaster(
    @PrimaryKey
    val id: Int,
    val genre: String,
    val jaGenre: String
)