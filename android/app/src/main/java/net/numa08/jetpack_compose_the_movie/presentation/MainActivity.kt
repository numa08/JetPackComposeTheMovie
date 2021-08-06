package net.numa08.jetpack_compose_the_movie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.numa08.jetpack_compose_the_movie.presentation.theme.MainApplicationTheme
import net.numa08.jetpack_compose_the_movie.presentation.top.TopPage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApplicationTheme {
                TopPage()
            }
        }
    }
}

@Composable
fun HelloComponent() {
    Text(text = "hello")
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHello(){
    HelloComponent()
}