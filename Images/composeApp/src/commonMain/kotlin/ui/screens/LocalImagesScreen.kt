package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import navigation.events.LocalImagesScreenEvent
import navigation.screenscomponents.LocalImagesScreenComponent
import ui.util.AppTopBar

@Composable
fun LocalImagesScreen(
    component: LocalImagesScreenComponent,
    layoutDirection: LayoutDirection = LocalLayoutDirection.current
) {

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Screens", modifier = Modifier.padding(16.dp))

                HorizontalDivider()

                NavigationDrawerItem(
                    label = { Text(text = "Main Screen") },
                    selected = false,
                    onClick = {component.onEvent(LocalImagesScreenEvent.NavToMainScreenButton)}
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
                Text(
                    text = "Local Images",
                    modifier = Modifier.weight(0.5f)
                )
                VerticalDivider(modifier = Modifier.weight(0.2f))
                Text(
                    text = "More local images",
                    modifier = Modifier.weight(0.3f)
                )
            }
        }
    }


}