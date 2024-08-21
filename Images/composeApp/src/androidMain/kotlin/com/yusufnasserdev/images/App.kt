package com.yusufnasserdev.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import navigation.DefaultRootComponent
import screens.LocalImagesScreen
import screens.MainScreen
import screens.OnlineImagesScreen


@Composable
fun App(rootComponent: DefaultRootComponent) {
    val childStack by rootComponent.childStack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(fade() + scale()),
    ) { child ->
        when (val childInstance = child.instance) {
            is DefaultRootComponent.Child.MainScreen -> MainScreen(
                component = childInstance.component
            )

            is DefaultRootComponent.Child.LocalImagesScreen -> LocalImagesScreen(
                component = childInstance.component
            )

            is DefaultRootComponent.Child.OnlineImagesScreen -> OnlineImagesScreen(
                component = childInstance.component
            )
        }

    }
}