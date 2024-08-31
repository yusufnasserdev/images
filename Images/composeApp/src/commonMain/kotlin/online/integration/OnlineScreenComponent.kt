package online.integration

import com.arkivanov.decompose.value.Value
import local.LocalImage
import online.OnlineImage

interface OnlineScreenComponent {

    /**
     * Main model of the online screen is the locally retrieved image
     */

    val model: Value<OnlineImage>

    /**
     * Navigating to local images screen implementation
     */

    fun onNavToLocal()
}