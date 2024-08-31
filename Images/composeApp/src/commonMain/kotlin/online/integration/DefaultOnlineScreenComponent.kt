package online.integration

import util.asValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import data.Image
import online.store.OnlineImagesStoreFactory

class DefaultOnlineScreenComponent(
    componentContext: ComponentContext,
    private val onlineImagesStoreFactory: OnlineImagesStoreFactory,
    private val onNavigateToLocalImagesScreen: () -> Unit
) : OnlineScreenComponent, ComponentContext by componentContext {


    private val store = instanceKeeper.getStore { onlineImagesStoreFactory.create() }
    override val model: Value<List<Image>> = store.asValue().map { it.items }


    override fun onNavToLocal() {
        onNavigateToLocalImagesScreen.invoke()
    }

    class Factory(
        private val onlineImagesStoreFactory: OnlineImagesStoreFactory
    ) : OnlineScreenComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext, navToLocalFabClicked: () -> Unit
        ): OnlineScreenComponent {
            return DefaultOnlineScreenComponent(
                componentContext = componentContext,
                onNavigateToLocalImagesScreen = navToLocalFabClicked,
                onlineImagesStoreFactory = onlineImagesStoreFactory
            )
        }
    }

}