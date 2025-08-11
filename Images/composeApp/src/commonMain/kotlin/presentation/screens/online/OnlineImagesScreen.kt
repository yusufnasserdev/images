package presentation.screens.online

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import presentation.shared.AppScreen
import presentation.shared.Screens

@Composable
fun OnlineImagesScreen(
    component: OnlineScreenComponent
) {

    val state by component.model.subscribeAsState()

    AppScreen(screen = Screens.ONLINE,
        imagesSourceList = state,
        onNav = { component.onNavToLocal() })
}

