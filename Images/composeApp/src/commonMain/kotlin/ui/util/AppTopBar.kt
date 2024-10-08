package ui.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

/**
 * Displays a top bar for the screen with its name.
 *
 * @param screenTitle the Title of the screen to be displayed in the top bar.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    screenTitle: String,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(
                text = screenTitle, style = MaterialTheme.typography.headlineLarge
            )
        }
    )
}