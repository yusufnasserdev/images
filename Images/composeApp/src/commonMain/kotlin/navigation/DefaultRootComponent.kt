package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.items
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import navigation.screenscomponents.LocalImagesScreenComponent
import navigation.screenscomponents.MainScreenComponent
import navigation.screenscomponents.OnlineImagesScreenComponent

class DefaultRootComponent(
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
        config: Configuration, context: ComponentContext
    ): Child = when (config) {
        is Configuration.MainScreen -> Child.MainScreen(
            MainScreenComponent(componentContext = context,
                onNavigateToLocalImagesScreen = {
                    if (childStack.items.any { it.configuration is Configuration.LocalImagesScreen }) {
                        navigation.pushToFront(Configuration.LocalImagesScreen)
                    } else navigation.pushNew(Configuration.LocalImagesScreen)
                },
                onNavigateToOnlineImagesScreen = {
                    if (childStack.items.any { it.configuration is Configuration.OnlineImagesScreen }) {
                        navigation.pushToFront(Configuration.OnlineImagesScreen)
                    } else navigation.pushNew(Configuration.OnlineImagesScreen)
                })
        )

        is Configuration.LocalImagesScreen -> Child.LocalImagesScreen(
            LocalImagesScreenComponent(componentContext = context,
                onNavigateToMainScreen = {
                    if (childStack.items.any { it.configuration is Configuration.MainScreen }) {
                        navigation.pushToFront(Configuration.MainScreen)
                    } else navigation.pushNew(Configuration.MainScreen)
                },
                onNavigateToOnlineImagesScreen = {
                    if (childStack.items.any { it.configuration is Configuration.OnlineImagesScreen }) {
                        navigation.pushToFront(Configuration.OnlineImagesScreen)
                    } else navigation.pushNew(Configuration.OnlineImagesScreen)
                })
        )

        is Configuration.OnlineImagesScreen -> Child.OnlineImagesScreen(
            OnlineImagesScreenComponent(componentContext = context,
                onNavigateToMainScreen = {
                    if (childStack.items.any { it.configuration is Configuration.MainScreen }) {
                        navigation.pushToFront(Configuration.MainScreen)
                    } else navigation.pushNew(Configuration.MainScreen)
                },
                onNavigateToLocalImagesScreen = {
                    if (childStack.items.any { it.configuration is Configuration.LocalImagesScreen }) {
                        navigation.pushToFront(Configuration.LocalImagesScreen)
                    } else navigation.pushNew(Configuration.LocalImagesScreen)
                })
        )
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