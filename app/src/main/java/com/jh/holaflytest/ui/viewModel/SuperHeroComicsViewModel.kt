package com.jh.holaflytest.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.holaflytest.domain.useCase.GetSuperHeroComicsUC
import com.jh.holaflytest.ui.state.SuperHeroComicsStateUI
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
 * Created by Jorge Henao on 14/11/23.
 */
@HiltViewModel
class SuperHeroComicsViewModel @Inject constructor(
    private val getSuperHeroComicsUC: GetSuperHeroComicsUC,
    private val _superHeroComicsState: MutableStateFlow<SuperHeroComicsStateUI>,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    val superHeroComicsState: StateFlow<SuperHeroComicsStateUI> = _superHeroComicsState.asStateFlow()

    fun getSuperHeroComics(characterId: Long) {
        getSuperHeroComicsUC.invoke(characterId).map { result ->
            result.fold(
                onSuccess = {
                    _superHeroComicsState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = it
                        )
                    }
                },
                onFailure = {
                    _superHeroComicsState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            isSuccess = null,
                            isFailure = true
                        )
                    }
                }
            )
        }.onStart {
            _superHeroComicsState.update { currentState -> currentState.copy(isLoading = true) }
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
