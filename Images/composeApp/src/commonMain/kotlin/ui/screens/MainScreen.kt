package ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.events.MainScreenEvent
import navigation.screenscomponents.MainScreenComponent
import ui.util.AppTopBar

@Composable
fun MainScreen(
    component: MainScreenComponent,
    layoutDirection: LayoutDirection = LocalLayoutDirection.current
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Screens", modifier = Modifier.padding(16.dp))

                HorizontalDivider(
                    modifier = Modifier.padding(2.dp)
                )

                NavigationDrawerItem(
                    label = { Text(text = "Main Screen") },
                    selected = true,
                    onClick = {}
                )

                NavigationDrawerItem(
                    label = { Text(text = "Local Images") },
                    selected = false,
                    onClick = {
                        component.onEvent(MainScreenEvent.NavToLocalImagesScreenButton)
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Online Images") },
                    selected = false,
                    onClick = {
                        component.onEvent(MainScreenEvent.NavToOnlineImagesScreenButton)
                    }
                )
            }
        }
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { AppTopBar(screenTitle = "Main Screen") }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding() + 16.dp,
                        bottom = innerPadding.calculateBottomPadding() + 16.dp,
                        start = innerPadding.calculateStartPadding(layoutDirection) + 16.dp,
                        end = innerPadding.calculateEndPadding(layoutDirection) + 16.dp
                    ),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Welcome to Images App",
                    fontSize = 24.sp
                )

                HorizontalDivider()

                Text(
                    text = "You can use the drawer to navigate to your local images or random online ones.",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

