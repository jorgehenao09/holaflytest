package com.jh.holaflytest.ui.state

import com.jh.holaflytest.domain.model.comics.Comic

/**
 * Created by Jorge Henao on 14/11/23.
 */
data class SuperHeroComicsStateUI(
    val isLoading: Boolean = false,
    val isSuccess: List<Comic>? = null,
    val isFailure: Boolean = false
)
