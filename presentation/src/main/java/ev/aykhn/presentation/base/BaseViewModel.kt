package ev.aykhn.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ev.aykhn.domain.base.BaseUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext

/*
    BaseViewModel.kt has been built for all basic view model functionalities;
    All state, effect hoisting and event processing handled by this base class;
 */

abstract class BaseViewModel<State, Event> : ViewModel() {

    private val mutex = Mutex()
    private val exceptionHandler = CoroutineExceptionHandler(::onError)

    abstract fun provideInitialState(): State

    private val _state = MutableStateFlow(provideInitialState())
    val state: StateFlow<State> = _state.asStateFlow()

    //optional override
    open fun onEvent(event: Event) {}

    open fun onError(context: CoroutineContext, throwable: Throwable) {

    }

    fun emitState(state: State) {
        launchOnMain {
            mutex.withLock {
                _state.emit(state)
            }
        }
    }

    fun <P, R, U : BaseUseCase<P, R>> use(
        useCase: U,
        params: P,
        onComplete: (R) -> Unit = {}
    ) {
        launchOnMain {
            val result = useCase.start(params)
            onComplete(result)
        }
    }

    protected fun launchOnMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(exceptionHandler, block = block)
    }

    protected fun launchOnDefault(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(exceptionHandler + Dispatchers.Default, block = block)
    }

    protected fun <T> Flow<T>.launchFlow(scope: CoroutineScope = viewModelScope): Job =
        this.catch {
            exceptionHandler.handleException(currentCoroutineContext(), it)
        }.launchIn(scope)

}