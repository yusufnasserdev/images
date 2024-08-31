package root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

import local.LocalImage
import local.integration.DefaultLocalScreenComponent
import local.integration.LocalScreenComponent

import online.OnlineImage
import online.integration.DefaultOnlineScreenComponent
import online.integration.OnlineScreenComponent

import root.integration.RootComponent.Child

/**
 * Interfaces for root and children components are mainly created to allow for testing flexibility when trying
 * to create test doubles, all  interfaces can be removed as testing isn't implemented yet, however I decided
 * to keep them to allow for this flexibility later.
 */

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class LocalImagesScreen(val component: LocalScreenComponent) : Child()
        class OnlineImagesScreen(val component: OnlineScreenComponent) : Child()
    }
}

/**
 * Main (parent) component for managing navigation between screens.
 */


class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()

    override val childStack : Value<ChildStack<*, Child>> = childStack(
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
     * would be more concise, the use of `pushNew` is ideal as navigation is done through a button.
     *
     * As it helps avoid pushing the same component if the user clicks the same button quickly multiple times.
     *
     * It's recommended not to use `pushNew` if the app contains more than two screens to avoid crashing
     * the app, Configurations must be unique (by equality) within the Child Stack.
     */

    private fun localImagesScreen(componentContext: ComponentContext): LocalScreenComponent =
        DefaultLocalScreenComponent(
            componentContext = componentContext,
            localImg = LocalImage(""),
            onNavigateToOnlineScreen = { navigation.pushNew(Configuration.OnlineImagesScreen) }
        )

    private fun onlineImagesScreen(componentContext: ComponentContext): OnlineScreenComponent =
        DefaultOnlineScreenComponent(
            componentContext = componentContext,
            onlineImage = OnlineImage(""),
            onNavigateToLocalImagesScreen = { navigation.pushNew(Configuration.LocalImagesScreen) })


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

}