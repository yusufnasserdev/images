package online.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import data.Image
import data.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import online.store.OnlineImagesStore.State

/**
 * OnlineImages Store definition, including the State implementation.
 *
 */

interface OnlineImagesStore : Store<Nothing, State, Any> {

    /**
     * Main state, the list of images sent to the `Composable` screen.
     */

    data class State(
        val items: List<Image> = emptyList()
    )
}

/**
 * Factory implementation for store creation, and core components implementation
 */

class OnlineImagesStoreFactory(
    private val storeFactory: StoreFactory,
    private val imageRepository: ImageRepository,
) {


    /**
     * Provides OnlineImagesStore creation.
     */

    fun create(): OnlineImagesStore =
        object : OnlineImagesStore, Store<Nothing, State, Any> by storeFactory.create(
            name = "OnlineImagesStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(imageRepository),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    /**
     * Actions going to the executor
     */

    private sealed interface Action {
        data class NewItemsReceived(val items: List<Image>) : Action  // Notifying the executor of new images.
    }

    /**
     * Messages going to the reducer
     */

    private sealed interface Msg {
        data class UpdateItems(val items: List<Image>) : Msg // Notifying the reducer to update the state.
    }


    private class BootstrapperImpl(
        private val repository: ImageRepository,
    ) : CoroutineBootstrapper<Action>() {

        override fun invoke() {
            scope.launch {
                repository.getAllOnlineImages()
                    .flowOn(Dispatchers.Default)
                    .collect { items ->
                        dispatch(Action.NewItemsReceived(items))  // Subscribes to state updates and dispatch actions.
                    }
            }
        }
    }


    private class ExecutorImpl : CoroutineExecutor<Nothing, Action, State, Msg, Any>() {

        override fun executeAction(action: Action) {
            when (action) {
                is Action.NewItemsReceived -> {
                    dispatch(Msg.UpdateItems(action.items)) // Sending new image actions to the reducer
                }
            }
        }
    }


    private object ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.UpdateItems -> copy(items = msg.items) // Updating the state with new images.
            }
    }

}