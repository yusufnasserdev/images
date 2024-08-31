package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import local.integration.LocalScreenComponent
import online.integration.OnlineScreenComponent

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

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): RootComponent
    }
}