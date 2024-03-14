package base

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

open class BaseViewModel : ViewModel() {

    protected fun launch(
        error: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val handlerException = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            when (throwable) {

                else -> error?.invoke(throwable)
            }
        }
        viewModelScope.launch(context = handlerException, block = {
            withContext(Dispatchers.IO) {
                block()
            }
        })
    }
}