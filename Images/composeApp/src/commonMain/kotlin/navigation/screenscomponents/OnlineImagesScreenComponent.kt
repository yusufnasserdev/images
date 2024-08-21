package navigation.screenscomponents

import com.arkivanov.decompose.ComponentContext
import navigation.events.OnlineImagesScreenEvent

class OnlineImagesScreenComponent (
    componentContext: ComponentContext,
    private val onNavigateToMainScreen: () -> Unit,
    private val onNavigateToLocalImagesScreen: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: OnlineImagesScreenEvent) {
        when (event) {
            OnlineImagesScreenEvent.NavToMainScreenButton -> onNavigateToMainScreen.invoke()
            OnlineImagesScreenEvent.NavToLocalImagesScreenButton -> onNavigateToLocalImagesScreen.invoke()
        }
    }

}