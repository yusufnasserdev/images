package online.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import data.Image

interface OnlineScreenComponent {

    /**
     * Main model of the online screen is the list of remote images
     */

    val model: Value<List<Image>>

    /**
     * Navigating to local images screen implementation
     */

    fun onNavToLocal()

    fun interface Factory {
        operator fun invoke( // 2
            componentContext: ComponentContext,
            navToLocalFabClicked: () -> Unit
        ): OnlineScreenComponent
    }
}