package local.store

import com.arkivanov.mvikotlin.core.store.Store

import local.store.LocalImagesStore.Intent
import local.store.LocalImagesStore.State
import local.store.LocalImagesStore.Label

import local.LocalImage

internal interface LocalImagesStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class SetText(val text: String) : Intent()
        data class SetDone(val isDone: Boolean) : Intent()
    }

    data class State(
        val text: String = "",

        )

    sealed class Label {
        data class Change(val img: LocalImage) : Label()
    }
}