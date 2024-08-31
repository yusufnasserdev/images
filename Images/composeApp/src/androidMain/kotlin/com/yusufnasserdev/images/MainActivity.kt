package com.yusufnasserdev.images

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.arkivanov.decompose.defaultComponentContext
import com.yusufnasserdev.images.theme.ComposeAppTheme
import org.kodein.di.instance
import util.kodeinDI
import root.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.Factory by kodeinDI.instance()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    App(rootComponent = rootComponent)
                }
            }
        }
    }
}
