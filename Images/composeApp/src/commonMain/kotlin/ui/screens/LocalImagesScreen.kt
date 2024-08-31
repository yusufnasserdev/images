package ui.screens

import androidx.compose.runtime.Composable
import com.yusufnasserdev.images.R
import local.integration.LocalScreenComponent
import ui.util.AppScreen
import ui.util.Screens


fun getPath(res: Int): String {
    return "android.resource://com.yusufnasserdev.images/$res"
}

@Composable
fun LocalImagesScreen(
    component: LocalScreenComponent,
) {

    val localImages = listOf(
        R.drawable.p0,
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.alg,
        R.drawable.p3,
        R.drawable.p4,
        R.drawable.p6,
        R.drawable.p7,
        R.drawable.p8,
        R.drawable.p9,
        R.drawable.p5
    ).map { getPath(it) }

    AppScreen(
        screen = Screens.LOCAL,
        imagesSourceList = localImages,
        onNav = { component.onNavToOnline() }
    )

}