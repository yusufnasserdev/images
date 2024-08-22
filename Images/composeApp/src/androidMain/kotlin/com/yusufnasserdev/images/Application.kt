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
    override fun newImageLoader(): ImageLoader {

        // CachePolicy.DISABLED prevents caching to allow for images to be
        // constantly updated for a refreshing experience.

        return ImageLoader(this).newBuilder()
            .components {
                add(SvgDecoder.Factory())
            }
            .memoryCachePolicy(CachePolicy.DISABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .logger(DebugLogger())
            .build()
    }


}