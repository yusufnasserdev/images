package com.yusufnasserdev.images

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

class Application : Application(), ImageLoaderFactory {

    /**
     * Creates an ImageLoader instance with specific configurations for image loading.
     *
     * @return An ImageLoader instance with the configured settings.
     */

    override fun newImageLoader(): ImageLoader {

        return ImageLoader(this)
            .newBuilder()
            .components {
                add(SvgDecoder.Factory()) // To support SVG format loading
            }
            .memoryCachePolicy(CachePolicy.ENABLED) // Enables memory caching to store images in memory, enhancing performance
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1) // Sets the maximum memory cache size to 10% of the available memory
                    .strongReferencesEnabled(true) // Enables strong references for cached images, preventing them from being garbage collected
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED) // Enables disk caching to store images on disk for later use.
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)// Sets the maximum disk cache size to 3% of the available disk space
                    .directory(cacheDir) // Specifies the directory for storing cached images on disk
                    .build()
            }
            .logger(DebugLogger()).build() // Enables debug logging for the ImageLoader
    }


}