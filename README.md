# images
KMP app to handle and view images, using MVIKotlin, Decompose and coil.

## Getting Started

Simply clone or download the repoistory, open the project using `Android Studio`, and run `composeApp` configuration.

The App consists of 3 screens, main screen, a screen to view locally stored images, and another to view images freshly retrieved from the internet.

You can navigate through the app using the navigation drawer by swiping from the left screen side to the right (or oppositely if RTL).

All images viewed are either SVG or JPG (JPEG).

---

## Built With

- [MVIKotlin](https://arkivanov.github.io/MVIKotlin/) - A Kotlin Multiplatform framework that provides a way of (not only) writing shared code using MVI pattern. It also includes powerful debug tools like logging and time travel.
- [Decompose](https://arkivanov.github.io/Decompose/) - A Kotlin Multiplatform library for breaking down your code into lifecycle-aware business logic components (aka BLoC), with routing functionality and pluggable UI (Compose, Android Views, SwiftUI, Kotlin/React, etc.).
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android and Compose Multiplatform.
