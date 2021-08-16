package net.numa08.jetpack_compose_the_movie.feature.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.Flow
import net.numa08.jetpack_compose_the_movie.domain.data.title.Poster
import net.numa08.jetpack_compose_the_movie.feature.ui.R

@Composable
fun GenreAndPosterRow(
    genre: String,
    posters: Flow<PagingData<Poster>>,
    onClickItem: (Poster) -> Unit,
    modifier: Modifier = Modifier
) {
    val posterItems = posters.collectAsLazyPagingItems()
    if (posterItems.itemCount == 0) {
        return
    }
    Column(modifier = modifier) {
        Text(
            text = genre,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            itemsIndexed(posterItems) { index, item ->
                val poster = requireNotNull(item)
                val posterUrl = poster.posterUrl.toString()
                val painter = if (poster.posterUrl == null) {
                    painterResource(id = R.drawable.dummy)
                } else {
                    rememberImagePainter(data = posterUrl)
                }
                Item(
                    index = index,
                    painter = painter,
                    onClickItem = {
                        onClickItem(poster)
                    },
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Item(
    index: Int,
    painter: Painter,
    onClickItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = { onClickItem(index) }, modifier = modifier) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(width = 121.dp, height = 220.dp)
        )
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Item(
        index = 0,
        painter = painterResource(id = R.drawable.dummy),
        onClickItem = {},
    )
}
