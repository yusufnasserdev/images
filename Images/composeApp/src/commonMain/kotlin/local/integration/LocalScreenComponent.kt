package local.integration

import com.arkivanov.decompose.value.Value
import local.LocalImage

interface LocalScreenComponent {

    /**
     * Main model of the local screen is the locally retrieved image
     */

    val model: Value<LocalImage>

    /**
     * Navigating to online images screen implementation
     */

    fun onNavToOnline()
}
