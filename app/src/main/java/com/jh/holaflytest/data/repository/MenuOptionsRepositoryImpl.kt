package com.jh.holaflytest.data.repository

import com.jh.holaflytest.data.localStorage.localFile.MenuFileManager
import com.jh.holaflytest.domain.model.MenuOptions
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.repository.MenuOptionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Jorge Henao on 12/11/23.
 */
class MenuOptionsRepositoryImpl(
    private val menuFileManager: MenuFileManager,
    private val domainExceptionRepository: DomainExceptionRepository
): MenuOptionsRepository {

    override fun getMenuOptions(): Flow<Result<MenuOptions>> =
        flow {
            val menuOptions = menuFileManager.getMenuOptions()
            emit(Result.success(menuOptions.toDomainMenuOptions()))
        }.catch {
            emit(Result.failure(domainExceptionRepository.manageError(it)))
        }
}
