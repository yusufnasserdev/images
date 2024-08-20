package navigation.screenscomponents

import com.arkivanov.decompose.ComponentContext
import navigation.events.MainScreenEvent

class MainScreenComponent (
    componentContext: ComponentContext,
    private val onNavigateToLocalImagesScreen: () -> Unit,
    private val onNavigateToOnlineImagesScreen: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            MainScreenEvent.NavToLocalImagesScreenButton -> onNavigateToLocalImagesScreen
            MainScreenEvent.NavToOnlineImagesScreenButton -> onNavigateToOnlineImagesScreen
        }
    }
}