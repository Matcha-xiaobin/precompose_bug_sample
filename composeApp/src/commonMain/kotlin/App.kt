import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {
    PreComposeApp {
        MaterialTheme {
            val scope = rememberCoroutineScope()
            val snackBarState by remember { mutableStateOf(SnackbarHostState()) }
            val navigator = rememberNavigator()
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navigator = navigator,
                    initialRoute = "pageA"
                ) {
                    scene("pageA") {
                        PageA(scope, snackBarState, navigator)
                    }
                    scene("pageB") {
                        PageB(scope, snackBarState, navigator)
                    }
                    scene("pageC") {
                        PageC(scope, snackBarState, navigator, "TEST")
                    }
                }
                SnackbarHost(
                    hostState = snackBarState,
                    modifier = Modifier.safeContentPadding()
                        .align(Alignment.TopCenter).padding(top = 56.dp)
                )
            }
        }
    }
}