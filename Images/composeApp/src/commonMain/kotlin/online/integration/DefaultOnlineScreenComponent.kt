package online.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import online.OnlineImage

class DefaultOnlineScreenComponent (
    componentContext: ComponentContext,
    onlineImage: OnlineImage,
    private val onNavigateToLocalImagesScreen: () -> Unit
) : OnlineScreenComponent, ComponentContext by componentContext {

    override val model: Value<OnlineImage> = MutableValue(onlineImage)
    override fun onNavToLocal() { onNavigateToLocalImagesScreen.invoke() }
}