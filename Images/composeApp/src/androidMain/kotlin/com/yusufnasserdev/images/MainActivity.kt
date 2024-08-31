package com.yusufnasserdev.images

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.arkivanov.decompose.retainedComponent
import com.yusufnasserdev.images.theme.ComposeAppTheme
import root.integration.DefaultRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = retainedComponent {
            DefaultRootComponent(it)
        }

        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    App(rootComponent = rootComponent)
                }
            }
        }
    }
}
