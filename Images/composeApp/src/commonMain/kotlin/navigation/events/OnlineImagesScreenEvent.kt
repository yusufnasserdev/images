package navigation.events

sealed interface OnlineImagesScreenEvent {
    data object NavToLocalImagesScreenButton: OnlineImagesScreenEvent
    data object NavToMainScreenButton: OnlineImagesScreenEvent
}