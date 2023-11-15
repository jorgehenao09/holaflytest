package com.jh.holaflytest.ui.state

import com.jh.holaflytest.domain.model.comicDetail.ComicDetail

/**
 * Created by Jorge Henao on 15/11/23.
 */
data class ComicDetailStateUI(
    val isLoading: Boolean = false,
    val isSuccess: List<ComicDetail>? = null,
    val isFailure: Boolean = false
)
