package net.numa08.jetpack_compose_the_movie.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    background = primaryColor,
    secondary = Teal200
)

@Composable
fun MainApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        content = content,
        typography = Typography,
        shapes = Shapes
    )
}

@Preview(showSystemUi = true)
@Composable
fun ThemePreview() {
    MainApplicationTheme {
        Text(text = "This is preview")
    }
}
