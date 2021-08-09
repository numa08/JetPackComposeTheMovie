SELECT
	original_title.titleid AS titleid,
	original_title.title AS originalTitle,
	localized_title.title AS localizedTitle
FROM
	(SELECT * FROM title_aka WHERE isOriginalTitle = 1) AS original_title
LEFT JOIN 
	(SELECT * FROM title_aka WHERE language = "ja" ) AS localized_title ON original_title.titleid = localized_title.titleid