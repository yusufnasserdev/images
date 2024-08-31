package root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import data.Image
import kotlinx.serialization.Serializable
import local.integration.DefaultLocalScreenComponent
import local.integration.LocalScreenComponent
import online.integration.DefaultOnlineScreenComponent
import online.integration.OnlineScreenComponent
import root.RootComponent
import root.RootComponent.Child


/**
 * Main (parent) component for managing navigation between screens.
 */

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val onlineScreenComponentFactory: OnlineScreenComponent.Factory,
    private val localScreenComponentFactory: LocalScreenComponent.Factory,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.LocalImagesScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Configuration, context: ComponentContext
    ): Child = when (config) {
        is Configuration.LocalImagesScreen -> Child.LocalImagesScreen(localImagesScreen(context))
        is Configuration.OnlineImagesScreen -> Child.OnlineImagesScreen(onlineImagesScreen(context))
    }

    /*
     * The following two functions are created for more code readability so the createChild method
     * would be more concise, the use of `pushToFront` is ideal ss it replaces the configuration if it
     * is already available at the back of the stack avoiding crashing the app.
     * As Configurations must be unique (by equality) within the Child Stack.
     */

    private fun localImagesScreen(componentContext: ComponentContext): LocalScreenComponent =
        localScreenComponentFactory(componentContext = componentContext,
            navToOnlineFabClicked = { navigation.pushToFront(Configuration.OnlineImagesScreen) })

    private fun onlineImagesScreen(componentContext: ComponentContext): OnlineScreenComponent =
        onlineScreenComponentFactory(componentContext = componentContext,
            navToLocalFabClicked = { navigation.pushToFront(Configuration.LocalImagesScreen) })

    /**
     * The main use of configurations is to represent a child component and contains all its arguments
     * While I'm not passing any arguments between screens while navigating, it's necessary to have a
     * `@Serializable` representation for each child for Decompose to be able to persists child configurations using StateKeeper.
     */

    @Serializable
    sealed class Configuration {
        @Serializable
        data object LocalImagesScreen : Configuration()

        @Serializable
        data object OnlineImagesScreen : Configuration()
    }

    class Factory(
        private val onlineScreenComponentFactory: OnlineScreenComponent.Factory,
        private val localScreenComponentFactory: LocalScreenComponent.Factory
    ) : RootComponent.Factory {
        override fun invoke(componentContext: ComponentContext): RootComponent {
            return DefaultRootComponent(
                componentContext = componentContext,
                onlineScreenComponentFactory = onlineScreenComponentFactory,
                localScreenComponentFactory = localScreenComponentFactory
            )
        }
    }

}