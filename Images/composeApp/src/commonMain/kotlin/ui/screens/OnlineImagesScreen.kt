package ui.screens

import androidx.compose.runtime.Composable
import online.integration.OnlineScreenComponent
import ui.util.AppScreen
import ui.util.Screens

@Composable
fun OnlineImagesScreen(
    component: OnlineScreenComponent
) {
    val onlineImages = listOf(
        "https://random.imagecdn.app/500/500", "https://www.svgrepo.com/show/530663/protein.svg"
    )

    AppScreen(screen = Screens.ONLINE,
        imagesSourceList = onlineImages,
        onNav = { component.onNavToLocal() })
}

