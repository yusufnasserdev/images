package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import navigation.screenscomponents.LocalImagesScreenComponent
import navigation.screenscomponents.MainScreenComponent
import navigation.screenscomponents.OnlineImagesScreenComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.MainScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )


    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when(config) {
            Configuration.MainScreen -> Child.MainScreen(
                MainScreenComponent(
                    componentContext = context,
                    onNavigateToLocalImagesScreen = { navigation.pushNew(Configuration.LocalImagesScreen) },
                    onNavigateToOnlineImagesScreen = { navigation.pushNew(Configuration.OnlineImagesScreen) }
                )
            )

            Configuration.LocalImagesScreen -> Child.LocalImagesScreen(
                LocalImagesScreenComponent(
                    componentContext = context,
                    onNavigateToMainScreen = { navigation.pushNew(Configuration.MainScreen) },
                    onNavigateToOnlineImagesScreen = { navigation.pushNew(Configuration.OnlineImagesScreen) }
                )
            )

            Configuration.OnlineImagesScreen -> Child.OnlineImagesScreen(
                OnlineImagesScreenComponent(
                    componentContext = context,
                    onNavigateToMainScreen = { navigation.pushNew(Configuration.MainScreen) },
                    onNavigateToLocalImagesScreen = { navigation.pushNew(Configuration.LocalImagesScreen) }
                )
            )
        }
    }


    sealed class Child {
        data class MainScreen(val component: MainScreenComponent) : Child()
        data class LocalImagesScreen(val component: LocalImagesScreenComponent) : Child()
        data class OnlineImagesScreen(val component: OnlineImagesScreenComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object MainScreen : Configuration()

        @Serializable
        data object LocalImagesScreen : Configuration()

        @Serializable
        data object OnlineImagesScreen : Configuration()

    }
}