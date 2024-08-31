package local.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import data.Image
import local.store.LocalImagesStoreFactory
import online.integration.DefaultOnlineScreenComponent
import online.integration.OnlineScreenComponent
import online.store.OnlineImagesStoreFactory
import util.asValue

class DefaultLocalScreenComponent(
    componentContext: ComponentContext,
    private val localImagesStoreFactory: LocalImagesStoreFactory,
    private val onNavigateToOnlineScreen: () -> Unit,
) : LocalScreenComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { localImagesStoreFactory.create() }
    override val model: Value<List<Image>> = store.asValue().map { it.items }

    override fun onNavToOnline() {
        onNavigateToOnlineScreen.invoke()
    }

    class Factory(
        private val localImagesStoreFactory: LocalImagesStoreFactory
    ) : LocalScreenComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext, navToOnlineFabClicked: () -> Unit
        ): LocalScreenComponent {
            return DefaultLocalScreenComponent(
                componentContext = componentContext,
                onNavigateToOnlineScreen = navToOnlineFabClicked,
                localImagesStoreFactory = localImagesStoreFactory
            )
        }

    }


}