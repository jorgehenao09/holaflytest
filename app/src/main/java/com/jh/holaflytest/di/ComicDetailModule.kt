package com.jh.holaflytest.di

import com.jh.holaflytest.data.api.MarvelApi
import com.jh.holaflytest.data.repository.ComicDetailRepositoryImpl
import com.jh.holaflytest.data.repository.exception.ExceptionComicDetailRepository
import com.jh.holaflytest.domain.repository.ComicDetailRepository
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.useCase.GetComicDetailUC
import com.jh.holaflytest.ui.state.ComicDetailStateUI
import com.jh.holaflytest.ui.viewModel.ComicDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Named

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Module
@InstallIn(ViewModelComponent::class)
object ComicDetailModule {

    @Named(EXCEPTION_COMIC_DETAIL_REPOSITORY)
    @Provides
    @ViewModelScoped
    fun provideExceptionComicDetailRepository(): DomainExceptionRepository = ExceptionComicDetailRepository()

    @Provides
    @ViewModelScoped
    fun provideComicDetailRepository(
        marvelApi: MarvelApi,
        @Named(EXCEPTION_COMIC_DETAIL_REPOSITORY) exceptionSuperHeroComic: DomainExceptionRepository
    ): ComicDetailRepository = ComicDetailRepositoryImpl(marvelApi, exceptionSuperHeroComic)

    @Provides
    @ViewModelScoped
    fun provideComicDetailUseCase(
        comicDetailRepository: ComicDetailRepository
    ) = GetComicDetailUC(comicDetailRepository)

    @Provides
    fun provideComicDetailViewModel(
        getComicDetailUC: GetComicDetailUC,
        coroutineDispatcher: CoroutineDispatcher
    ) = ComicDetailViewModel(
        getComicDetailUC,
        MutableStateFlow(ComicDetailStateUI(isLoading = false)),
        coroutineDispatcher
    )
}

private const val EXCEPTION_COMIC_DETAIL_REPOSITORY = "exceptionComicDetailRepository"
