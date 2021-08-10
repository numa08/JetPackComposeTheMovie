package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "genre_master")
data class GenreMaster(
    @PrimaryKey
    val genre: String,
    val jaGenre: String
)

@Entity(
    tableName = "genre_title",
    primaryKeys = ["genre", "titleId"],
    indices = [
        Index(value = ["titleId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = GenreMaster::class,
            childColumns = ["genre"],
            parentColumns = ["genre"]
        ),
        ForeignKey(
            entity = OriginalTitle::class,
            parentColumns = ["tconst"],
            childColumns = ["titleId"]
        )
    ]
)
data class GenreTitle(
    val genre: String,
    val titleId: String
)