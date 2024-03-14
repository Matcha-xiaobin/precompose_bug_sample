import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel
import vm.PageCVM

@Composable
fun PageA(
    scope: CoroutineScope,
    snackBar: SnackbarHostState,
    navigator: Navigator,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                scope.launch {
                    navigator.navigateForResult("pageB")
                    delay(351)
                    navigator.navigate("pageC")
                }
            }) {
                Text("测试 Start")
            }
        }
    }
}

@Composable
fun PageB(
    scope: CoroutineScope,
    snackBar: SnackbarHostState,
    navigator: Navigator,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                navigator.goBackWith("OK")
            }) {
                Text("返回上一页")
            }
        }
    }
}

@Composable
fun PageC(
    scope: CoroutineScope,
    snackBar: SnackbarHostState,
    navigator: Navigator,
    id: String,
) {
    val vm = viewModel(PageCVM::class, keys = listOf(id)) {
        PageCVM(id)
    }
    val state by remember { vm.state }
    LaunchedEffect(Unit) {
        if (state != 1) {
            vm.checkUpdate()
        }
    }
    val updateText by remember { vm.hasUpdate }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("下方文本内容显示 \"launch 执行了\" 则说明成功了")
            Spacer(modifier = Modifier.height(12.dp))
            Text("文本内容：$updateText")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                vm.click()
            }) {
                Text("手动调用")
            }

            Button(onClick = {
                navigator.goBack()
            }) {
                Text("返回上一页")
            }
        }
    }
}