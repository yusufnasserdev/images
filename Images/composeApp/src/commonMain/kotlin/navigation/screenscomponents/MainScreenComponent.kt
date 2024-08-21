package navigation.screenscomponents

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import navigation.DefaultRootComponent
import navigation.events.MainScreenEvent

class MainScreenComponent(
    componentContext: ComponentContext,
    private val onNavigateToLocalImagesScreen: () -> Unit,
    private val onNavigateToOnlineImagesScreen: () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: MainScreenEvent) {
        Log.i("Nav", "trying, in component")

        when (event) {
            is MainScreenEvent.NavToLocalImagesScreenButton -> onNavigateToLocalImagesScreen.invoke()
            is MainScreenEvent.NavToOnlineImagesScreenButton -> onNavigateToOnlineImagesScreen.invoke()
        }
    }
}