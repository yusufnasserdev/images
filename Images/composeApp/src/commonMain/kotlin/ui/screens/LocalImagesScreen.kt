package ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import local.integration.LocalScreenComponent
import ui.util.AppScreen
import ui.util.Screens


@Composable
fun LocalImagesScreen(
    component: LocalScreenComponent,
) {
    val state by component.model.subscribeAsState()

    AppScreen(
        screen = Screens.LOCAL,
        imagesSourceList = state,
        onNav = { component.onNavToOnline() }
    )

}