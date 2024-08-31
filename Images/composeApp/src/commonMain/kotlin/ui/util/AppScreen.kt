package ui.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yusufnasserdev.images.R
import data.Image
import androidx.compose.ui.res.painterResource


/**
 * Enumeration for available app screens.
 */

enum class Screens {
    LOCAL, ONLINE
}


/**
 * Provides a FAB to facilitate navigation between screens
 *
 * Mainly implemented to improve readability.
 *
 * @param onNav lambda for the navigation callback
 * @param goToScreen The destination screen name
 *
 * @return FAB to navigate between screens
 */

@Composable
private fun NavFab(
    onNav: () -> Unit, goToScreen: String
) {
    FloatingActionButton(modifier = Modifier.padding(
        vertical = 8.dp, horizontal = 8.dp
    ), onClick = { onNav() }) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp), text = "To $goToScreen Images"
        )
    }
}


/**
 * Re-usable `@Composable` for screen layout.
 *
 * @param screen Enum to represent the screen
 * @param imagesSourceList the data source used for the images to be displayed
 * @param onNav the navigation lambda to be called when the nav button is clicked
 */

@Composable
fun AppScreen(
    screen: Screens, imagesSourceList: List<Image>, onNav: () -> Unit
) {

    val currentScreen = if (screen == Screens.LOCAL) "Local" else "Online"
    val goToScreen = if (screen == Screens.LOCAL) "Online" else "Local"

    /**
     * Using a `Scaffold` allows for plugin and play kind of a layout, with basic material design visual layout structure.
     */

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { AppTopBar(screenTitle = "$currentScreen Images") },
        floatingActionButton = { NavFab(onNav, goToScreen) }) { innerPadding ->

        // Creating a `Column` inside of `Scaffold` allow for better and more controlled view

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 16.dp,
                    bottom = innerPadding.calculateBottomPadding() + 16.dp,
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current) + 16.dp,
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current) + 16.dp
                )
        ) {

            /**
             * Using a `LazyVerticalGrid` with 2 fixed grid cells to support displaying images in a
             * scrollable grid, being lazy allows to only compose and lay out items which are visible
             * in the component’s viewport
             */

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
                state = rememberLazyGridState()
            ) {
                items(imagesSourceList.size) { imgIdx ->
                    AsyncImage(
                        model = imagesSourceList[imgIdx].url,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.ph),
                        error = painterResource(id = R.drawable.err),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .aspectRatio(1f)
                    )
                }
            }


        }
    }
}