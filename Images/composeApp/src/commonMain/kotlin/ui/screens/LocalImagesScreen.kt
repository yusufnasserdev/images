package ui.screens

import android.util.Log
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yusufnasserdev.images.R
import navigation.events.LocalImagesScreenEvent
import navigation.screenscomponents.LocalImagesScreenComponent
import ui.util.AppTopBar


@Composable
fun LocalImagesScreen(
    component: LocalImagesScreenComponent,
    layoutDirection: LayoutDirection = LocalLayoutDirection.current
) {

    val lazyGridState = rememberLazyGridState()

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
        R.drawable.p5)


ModalNavigationDrawer(
drawerContent = {
    ModalDrawerSheet {
        Text("Screens", modifier = Modifier.padding(16.dp))

        HorizontalDivider()

        NavigationDrawerItem(
            label = { Text(text = "Main Screen") },
            selected = false,
            onClick = { component.onEvent(LocalImagesScreenEvent.NavToMainScreenButton) }
        )

        NavigationDrawerItem(
            label = { Text(text = "Local Images") },
            selected = true,
            onClick = {}
        )

        NavigationDrawerItem(
            label = { Text(text = "Online Images") },
            selected = false,
            onClick = {
                component.onEvent(LocalImagesScreenEvent.NavToOnlineImagesScreenButton)
            }
        )

    }
}
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppTopBar(screenTitle = "Local Images") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 16.dp,
                    bottom = innerPadding.calculateBottomPadding() + 16.dp,
                    start = innerPadding.calculateStartPadding(layoutDirection) + 16.dp,
                    end = innerPadding.calculateEndPadding(layoutDirection) + 16.dp
                )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                // content padding
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                state = lazyGridState
            ) {
                items(localImages.size) { imgIdx ->
                    Log.i("LocalImg", localImages[imgIdx].toString())
                    AsyncImage(
                        model = localImages[imgIdx],
                        contentDescription = null,
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


}