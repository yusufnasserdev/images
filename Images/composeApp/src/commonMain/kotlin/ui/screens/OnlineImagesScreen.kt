package ui.screens

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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import navigation.events.OnlineImagesScreenEvent
import navigation.screenscomponents.OnlineImagesScreenComponent
import ui.util.AppTopBar

@Composable
fun OnlineImagesScreen(
    component: OnlineImagesScreenComponent,
    layoutDirection: LayoutDirection = LocalLayoutDirection.current
) {
    val lazyGridState = rememberLazyGridState()

    val onlineImages = listOf(
        "https://random.imagecdn.app/500/500",
        "https://www.svgrepo.com/show/530663/protein.svg"
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Screens", modifier = Modifier.padding(16.dp))
                HorizontalDivider()

                NavigationDrawerItem(
                    label = { Text(text = "Main Screen") },
                    selected = false,
                    onClick = {
                        component.onEvent(OnlineImagesScreenEvent.NavToMainScreenButton)
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Local Images") },
                    selected = false,
                    onClick = {
                        component.onEvent(OnlineImagesScreenEvent.NavToLocalImagesScreenButton)
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Online Images") },
                    selected = true,
                    onClick = {}
                )

            }
        }
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { AppTopBar(screenTitle = "Online Images") }
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
                    items(onlineImages.size) { idx ->
                        AsyncImage(
                            model = onlineImages[idx],
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp)
                                .aspectRatio(1f)
                        )
                    }
                }
            }
        }
    }

}