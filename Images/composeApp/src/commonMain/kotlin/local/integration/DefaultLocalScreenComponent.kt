package local.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import local.LocalImage

class DefaultLocalScreenComponent (
    componentContext: ComponentContext,
    localImg: LocalImage,
    private val onNavigateToOnlineScreen: () -> Unit,
) : LocalScreenComponent, ComponentContext by componentContext {

    override val model: Value<LocalImage> = MutableValue(localImg)

    override fun onNavToOnline() {
        onNavigateToOnlineScreen.invoke()
    }

}