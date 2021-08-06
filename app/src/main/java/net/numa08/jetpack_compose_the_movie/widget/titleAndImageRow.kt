package net.numa08.jetpack_compose_the_movie.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import net.numa08.jetpack_compose_the_movie.R
import net.numa08.jetpack_compose_the_movie.presentation.theme.MainApplicationTheme

@Composable
fun TitleAndImageRow(
    title: String,
    images: Pager<Int, Painter>,
    onClickItem: (Int) -> Unit,
    onClickItemContextMenu: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyImages = images.flow.collectAsLazyPagingItems()
    Column(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.h5)
        LazyRow {
            itemsIndexed(lazyImages) { index, item ->
                Item(
                    index = index,
                    painter = item!!,
                    onClickItem = onClickItem,
                    onClickItemContextMenu = onClickItemContextMenu
                )
            }
        }
    }
}

@Composable
private fun Item(
    index: Int,
    painter: Painter,
    onClickItem: (Int) -> Unit,
    onClickItemContextMenu: (Int, Int) -> Unit
) {
    Column(modifier = Modifier.clickable { onClickItem(index) }) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(width = 128.dp, height = 243.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Item(
        index = 0,
        painter = painterResource(id = R.drawable.ic_launcher_background),
        onClickItem = {},
        onClickItemContextMenu = { _, _ -> })
}

@Preview(showSystemUi = true)
@Composable
fun TitleAndImageRowPreview() {
    val painters = (0 until 20).map {
        painterResource(id = R.drawable.ic_launcher_background)
    }
    MainApplicationTheme {
        TitleAndImageRow(
            title = "Preview",
            images = Pager(
                config = PagingConfig(20)
            ) {
                object : PagingSource<Int, Painter>() {
                    override fun getRefreshKey(state: PagingState<Int, Painter>): Int? = null

                    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Painter> =
                        LoadResult.Page(
                            data = painters,
                            prevKey = null,
                            nextKey = null
                        )
                }
            },
            onClickItem = {},
            onClickItemContextMenu = { _, _ ->
            })
    }
}
