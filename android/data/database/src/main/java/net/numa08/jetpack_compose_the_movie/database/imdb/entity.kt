package net.numa08.jetpack_compose_the_movie.database.imdb

import androidx.room.*

@Entity(tableName = "title")
data class OriginalTitle(
    @PrimaryKey
    @ColumnInfo(name = "tconst", index = true)
    val titleId: String,
    val primaryTitle: String?,
    val originalTitle: String?,
    val isAdult: Boolean,
    val startYear: Int?,
    val endYear: Int?,
    val runtimeMinutes: Int?,
    val titleType: String?
)

@Entity(
    tableName = "title_aka",
    indices = [
        Index(value = ["titleId", "language", "region"])
    ],
    foreignKeys = [ForeignKey(
        entity = OriginalTitle::class,
        parentColumns = ["tconst"],
        childColumns = ["titleId"],
    )]
)
data class Title(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titleId: String,
    val title: String?,
    val language: String?,
    val region: String?,
    val isOriginalTitle: Boolean,
)

@DatabaseView(
    "SELECT " +
            "original_title.titleid AS titleId, " +
            "original_title.title AS originalTitle, " +
            "localized_title.title AS localizedTitle " +
            "FROM" +
            "(SELECT * FROM title_aka WHERE isOriginalTitle = 1) AS original_title " +
            "LEFT JOIN " +
            "(SELECT * FROM title_aka WHERE language = \"ja\" ) AS localized_title ON original_title.titleid = localized_title.titleid",
    viewName = "japanese_title"
)
data class JapaneseTitle(
    val titleId: String,
    val originalTitle: String?,
    val localizedTitle: String?
)

data class TitleData(
    @Embedded val originalTitle: OriginalTitle,
    @Relation(
        parentColumn = "tconst",
        entityColumn = "titleId",
        entity = Title::class
    )
    val title: Title,
    @Relation(
        parentColumn = "tconst",
        entityColumn = "titleId",
        entity = GenreTitle::class
    )
    val genres: List<GenreTitle>
)

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