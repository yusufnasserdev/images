package ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import online.integration.OnlineScreenComponent
import ui.util.AppScreen
import ui.util.Screens

@Composable
fun OnlineImagesScreen(
    component: OnlineScreenComponent
) {

    val state by component.model.subscribeAsState()

    AppScreen(screen = Screens.ONLINE,
        imagesSourceList = state,
        onNav = { component.onNavToLocal() })
}

