package local.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import data.Image

interface LocalScreenComponent {

    /**
     * Main model of the local screen is the locally retrieved list of images
     */

    val model: Value<List<Image>>

    /**
     * Navigating to online images screen implementation
     */

    fun onNavToOnline()

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navToOnlineFabClicked: () -> Unit
        ): LocalScreenComponent
    }
}
