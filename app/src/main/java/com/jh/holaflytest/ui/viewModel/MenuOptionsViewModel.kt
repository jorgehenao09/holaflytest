package com.jh.holaflytest.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.holaflytest.domain.useCase.GetMenuOptionsUC
import com.jh.holaflytest.ui.state.MenuOptionsStateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Jorge Henao on 13/11/23.
 */
@HiltViewModel
class MenuOptionsViewModel @Inject constructor(
    private val getMenuOptionsUC: GetMenuOptionsUC,
    private val _menuOptionsState: MutableStateFlow<MenuOptionsStateUI>,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    val menuOptionsSate: StateFlow<MenuOptionsStateUI> = _menuOptionsState.asStateFlow()

    fun getMenuOptions() {
        getMenuOptionsUC.invoke().map { result ->
            result.fold(
                onSuccess = {
                    _menuOptionsState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = it
                        )
                    }
                },
                onFailure = {
                    _menuOptionsState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = null,
                            isFailure = true
                        )
                    }
                }
            )
        }.onStart {
            _menuOptionsState.update { currentState -> currentState.copy(isLoading = true) }
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
