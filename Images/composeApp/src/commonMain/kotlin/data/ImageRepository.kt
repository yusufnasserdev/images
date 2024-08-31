package data

import com.yusufnasserdev.images.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Used to generate paths for android resources images.
 *
 * While coil can work with android resources id directly, the conversion is done to unify the
 * way `AsyncImage` loads an image to allow for re-usability.
 *
 * @return String path for android resources
 * @param res Android resourced ID
 */

fun getPath(res: Int): String {
    return "android.resource://com.yusufnasserdev.images/$res"
}

interface ImageRepository {

    /**
     * @return A flow of List of Strings that represents the URLs of the intended images.
     */

    fun getAllImages(): Flow<List<Image>>
}

class LocalImageRepository : ImageRepository {

    private val images = MutableStateFlow(
        listOf(
            R.drawable.p0, //JPG
            R.drawable.p1, //JPG
            R.drawable.p2, //JPG
            R.drawable.alg, //SVG
            R.drawable.p3, //JPG
            R.drawable.p4, //JPG
            R.drawable.p6, //JPG
            R.drawable.p7, //JPG
            R.drawable.p8, //JPG
            R.drawable.build, //SVG
            R.drawable.p9, //JPG
            R.drawable.p5 //JPG
        ).map { Image(getPath(it)) } // Mapping drawable resource IDs to their paths.
    )

    override fun getAllImages(): Flow<List<Image>> = images

}

class OnlineImageRepository : ImageRepository {
    private val onlineImages = MutableStateFlow(
        listOf(
            Image("https://loremflickr.com/cache/resized/65535_53456925841_c8def9194d_c_500_500_nofilter.jpg"),
            Image("https://fastly.picsum.photos/id/654/500/500.jpg?hmac=kgpTNBLWV4m-_q3UocuHI4AjklIGOB5U_fE0mfErB88"),
            Image("https://fastly.picsum.photos/id/524/500/500.jpg?hmac=z2uVLm01qVMf28HEDb_MReUAvHbG0uylevoJupVdCqA"),
            Image("https://loremflickr.com/cache/resized/65535_53629729913_fe44eb7708_c_500_500_nofilter.jpg"),
            Image("https://fastly.picsum.photos/id/404/500/500.jpg?hmac=7HRz92DqElTDqk9X2uaWSgb-ClfQfhmwFNhcCtKUIwE"),
            Image("https://fastly.picsum.photos/id/355/500/500.jpg?hmac=SndJcztStFAkO0CHFOdumXqLZ23UliAqHi9bEUUGiA8"),
            Image("https://fastly.picsum.photos/id/419/500/500.jpg?hmac=lDjNnMBSOdkeRGs2KTGr0ZE9JABxd3WCT4eQZnIJdUs"),
            Image("https://loremflickr.com/cache/resized/65535_53286647456_b3761f9919_z_500_500_nofilter.jpg"),
            Image("https://fastly.picsum.photos/id/37/500/500.jpg?hmac=lzRnNBxtKpvY781uybLQr9mfijZxiqHV8GF_wGkijtY"),
            Image("https://www.svgrepo.com/show/530663/protein.svg"),
            Image("https://www.svgrepo.com/show/530465/interest-rate.svg"),
            Image("https://www.svgrepo.com/show/530470/my-category.svg"),
            Image("https://www.svgrepo.com/show/530471/credit-card.svg"),
            Image("https://www.svgrepo.com/show/530474/cheque.svg"),
            Image("https://www.svgrepo.com/show/530480/law.svg"),
            Image("https://www.svgrepo.com/show/530469/futures.svg"),
            Image("https://www.svgrepo.com/show/530464/insurance.svg")
        )
    )

    override fun getAllImages(): Flow<List<Image>> = onlineImages

}