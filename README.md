# images
KMP app to handle and view images, using MVIKotlin, Decompose and coil.

## Getting Started

Simply clone or download the repoistory, open the project using `Android Studio`, and run `composeApp` configuration.

The App consists of 2 screens, a screen to view locally stored images, and another to view images freshly retrieved from the internet.

You can navigate through the app using the floating button to go from one screen to the other.

All images viewed are either SVG or JPG (JPEG).

---

## Built With
- [MVIKotlin](https://arkivanov.github.io/MVIKotlin/) - A Kotlin Multiplatform framework that provides a way of (not only) writing shared code using MVI pattern. It also includes powerful debug tools like logging and time travel.
- [Decompose](https://arkivanov.github.io/Decompose/) - A Kotlin Multiplatform library for breaking down your code into lifecycle-aware business logic components (aka BLoC), with routing functionality and pluggable UI (Compose, Android Views, SwiftUI, Kotlin/React, etc.).
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android and Compose Multiplatform.
- [Kodein-DI](https://kosi-libs.org/kodein/7.22/index.html) - A straightforward and yet very useful dependency retrieval container. it is effortless to use and configure.
