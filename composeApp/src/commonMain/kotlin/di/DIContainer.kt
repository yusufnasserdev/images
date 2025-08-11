package di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import data.DefaultImageRepository
import data.ImageRepository
import presentation.screens.local.DefaultLocalScreenComponent
import presentation.screens.local.LocalScreenComponent
import presentation.screens.local.LocalImagesStoreFactory
import presentation.screens.online.DefaultOnlineScreenComponent
import presentation.screens.online.OnlineScreenComponent
import presentation.screens.online.OnlineImagesStoreFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import presentation.root.RootComponent
import presentation.root.DefaultRootComponent

/**
 * Using Kodein and Dependency Injection for easier dependencies management instead of having
 * to inject them manually, also eases the way for testing modules when implemented.
 */

val kodeinDI = DI {
    bindSingleton<ImageRepository> { DefaultImageRepository() }

    bindSingleton<StoreFactory> {
        LoggingStoreFactory(TimeTravelStoreFactory())
    }

    bindSingleton<LocalScreenComponent.Factory> {
        DefaultLocalScreenComponent.Factory(localImagesStoreFactory = instance())
    }

    bindSingleton {
        LocalImagesStoreFactory(
            storeFactory = instance(),
            imageRepository = instance(),
        )
    }

    bindSingleton<OnlineScreenComponent.Factory> {
        DefaultOnlineScreenComponent.Factory(onlineImagesStoreFactory = instance())
    }

    bindSingleton {
        OnlineImagesStoreFactory(
            storeFactory = instance(),
            imageRepository = instance(),
        )
    }

    bindSingleton<RootComponent.Factory> {
        DefaultRootComponent.Factory(
            onlineScreenComponentFactory = instance(),
            localScreenComponentFactory = instance()
        )
    }

}