package com.jh.holaflytest.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.holaflytest.domain.useCase.GetComicDetailUC
import com.jh.holaflytest.ui.state.ComicDetailStateUI
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
 * Created by Jorge Henao on 15/11/23.
 */
@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    private val getComicDetailUC: GetComicDetailUC,
    private val _comicDetailState: MutableStateFlow<ComicDetailStateUI>,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    val comicDetailState: StateFlow<ComicDetailStateUI> = _comicDetailState.asStateFlow()

    fun getComicDetail(comicId: String) {
        getComicDetailUC.invoke(comicId).map { result ->
            result.fold(
                onSuccess = {
                    _comicDetailState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = it
                        )
                    }
                },
                onFailure = {
                    _comicDetailState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = null,
                            isFailure = true
                        )
                    }
                }
            )
        }.onStart {
            _comicDetailState.update { currentState -> currentState.copy(isLoading = true) }
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
