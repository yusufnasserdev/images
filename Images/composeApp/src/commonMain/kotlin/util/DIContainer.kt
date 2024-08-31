package util

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import data.DefaultImageRepository
import data.ImageRepository
import local.integration.DefaultLocalScreenComponent
import local.integration.LocalScreenComponent
import local.store.LocalImagesStoreFactory
import online.integration.DefaultOnlineScreenComponent
import online.integration.OnlineScreenComponent
import online.store.OnlineImagesStoreFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import root.RootComponent
import root.integration.DefaultRootComponent


val kodeinDI = DI {
    // Repository
    bindSingleton<ImageRepository> { DefaultImageRepository() }

    // Store
    bindSingleton<StoreFactory> {
        LoggingStoreFactory(TimeTravelStoreFactory())
    }

    // Local Screen
    bindSingleton<LocalScreenComponent.Factory> {
        DefaultLocalScreenComponent.Factory(localImagesStoreFactory = instance())
    }

    bindSingleton {
        LocalImagesStoreFactory(
            storeFactory = instance(),
            imageRepository = instance(),
        )
    }

    // Online Screen

    bindSingleton<OnlineScreenComponent.Factory> {
        DefaultOnlineScreenComponent.Factory(onlineImagesStoreFactory = instance())
    }

    bindSingleton {
        OnlineImagesStoreFactory(
            storeFactory = instance(),
            imageRepository = instance(),
        )
    }


    // Root

    bindSingleton<RootComponent.Factory> {

        DefaultRootComponent.Factory(
            onlineScreenComponentFactory = instance(),
            localScreenComponentFactory = instance()
        )

    }

}