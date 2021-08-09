package net.numa08.jetpack_compose_the_movie.data

import androidx.room.DatabaseView

@DatabaseView(
    """
SELECT
    original_title.titleid AS titleId,
	original_title.title AS originalTitle,	
	title_as_localized.title AS localizedTitle
FROM (SELECT * FROM title_aka WHERE isOriginalTitle = 1) AS original_title
 LEFT JOIN (
	SELECT * FROM title_aka WHERE language = "ja"
 ) AS title_as_localized ON original_title.titleid = title_as_localized.titleid
"""
)
data class JapaneseTitle(
    val titleId: String,
    val originalTitle: String?,
    val localizedTitle: String?
)
