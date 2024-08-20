package navigation.events

sealed interface LocalImagesScreenEvent {
    data object NavToOnlineImagesScreenButton: LocalImagesScreenEvent
    data object NavToMainScreenButton: LocalImagesScreenEvent
}