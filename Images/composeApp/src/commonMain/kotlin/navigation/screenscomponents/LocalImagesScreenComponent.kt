package navigation.screenscomponents

import com.arkivanov.decompose.ComponentContext
import navigation.events.LocalImagesScreenEvent
import navigation.events.MainScreenEvent

class LocalImagesScreenComponent (
    componentContext: ComponentContext,
    private val onNavigateToMainScreen: () -> Unit,
    private val onNavigateToOnlineImagesScreen: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: LocalImagesScreenEvent) {
        when (event) {
            LocalImagesScreenEvent.NavToMainScreenButton -> onNavigateToMainScreen
            LocalImagesScreenEvent.NavToOnlineImagesScreenButton -> onNavigateToOnlineImagesScreen
        }
    }

}