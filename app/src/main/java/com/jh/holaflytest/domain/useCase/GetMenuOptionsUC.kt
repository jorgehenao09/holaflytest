package com.jh.holaflytest.domain.useCase

import com.jh.holaflytest.domain.model.MenuOptions
import com.jh.holaflytest.domain.repository.MenuOptionsRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Jorge Henao on 13/11/23.
 */
class GetMenuOptionsUC(
    private val menuOptionsRepository: MenuOptionsRepository
) {

    operator fun invoke() = flow<Result<MenuOptions>> {
        menuOptionsRepository.getMenuOptions().collect { menuOptionsResult ->
            menuOptionsResult.fold(
                onSuccess = { menuOptions ->
                    emit(Result.success(menuOptions))
                },
                onFailure = { domainException ->
                    emit(Result.failure(domainException))
                }
            )
        }
    }
}
