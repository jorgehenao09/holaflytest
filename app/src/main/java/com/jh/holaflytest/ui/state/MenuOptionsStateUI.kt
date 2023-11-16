package com.jh.holaflytest.ui.state

import com.jh.holaflytest.domain.model.MenuOptions

/**
 * Created by Jorge Henao on 13/11/23.
 */
data class MenuOptionsStateUI(
    val isLoading: Boolean = false,
    val isSuccess: MenuOptions? = null,
    val isFailure: Boolean = false
)
