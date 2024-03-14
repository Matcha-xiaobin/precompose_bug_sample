package vm

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import base.BaseViewModel
import kotlinx.coroutines.delay


class PageCVM(val id: String) : BaseViewModel() {

    val hasUpdate = mutableStateOf("")
    val state = mutableIntStateOf(0)

    fun checkUpdate() {
        hasUpdate.value = "调用了checkUpdate"
        launch(error = {
        }) {
            state.value = 1
            val index = 0
            while (index < 10) {
                hasUpdate.value = "launch 执行了"
                delay(200)
            }
            state.value = 2
        }
    }

    fun click() {
        hasUpdate.value = "手动调用了..."
    }
}