package navigation.events

sealed interface MainScreenEvent {
    data object NavToOnlineImagesScreenButton: MainScreenEvent
    data object NavToLocalImagesScreenButton: MainScreenEvent
}