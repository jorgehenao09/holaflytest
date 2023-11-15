package com.jh.holaflytest.ui.viewModel

import androidx.lifecycle.ViewModel
import com.jh.holaflytest.domain.useCase.GetComicDetailUC
import com.jh.holaflytest.ui.state.ComicDetailStateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
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
}
