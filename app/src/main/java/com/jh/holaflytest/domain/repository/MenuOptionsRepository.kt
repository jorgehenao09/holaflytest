package com.jh.holaflytest.domain.repository

import com.jh.holaflytest.domain.model.MenuOptions
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jorge Henao on 12/11/23.
 */
interface MenuOptionsRepository {

    fun getMenuOptions(): Flow<Result<MenuOptions>>
}
