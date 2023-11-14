package com.jh.holaflytest.di

import com.jh.holaflytest.data.localStorage.localFile.MenuFileManager
import com.jh.holaflytest.data.repository.MenuOptionsRepositoryImpl
import com.jh.holaflytest.data.repository.exception.ExceptionHomeRepository
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.repository.MenuOptionsRepository
import com.jh.holaflytest.domain.useCase.GetMenuOptionsUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

/**
 * Created by Jorge Henao on 12/11/23.
 */
@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    fun provideMenuFileManager() = MenuFileManager()

    @Named(EXCEPTION_HOME_REPOSITORY)
    @Provides
    @ViewModelScoped
    fun provideExceptionHomeRepository(): DomainExceptionRepository = ExceptionHomeRepository()

    @Provides
    @ViewModelScoped
    fun provideMenuOptionsRepository(
        menuFileManager: MenuFileManager,
        @Named(EXCEPTION_HOME_REPOSITORY) exceptionHome: DomainExceptionRepository
    ): MenuOptionsRepository = MenuOptionsRepositoryImpl(menuFileManager, exceptionHome)

    @Provides
    @ViewModelScoped
    fun provideGetMenuOptionsUseCase(
        menuOptionsRepository: MenuOptionsRepository
    ) = GetMenuOptionsUC(menuOptionsRepository)
}

private const val EXCEPTION_HOME_REPOSITORY = "exceptionHomeRepository"
