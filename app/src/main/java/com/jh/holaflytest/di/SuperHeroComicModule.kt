package com.jh.holaflytest.di

import com.jh.holaflytest.data.api.MarvelApi
import com.jh.holaflytest.data.repository.SuperHeroComicsRepositoryImpl
import com.jh.holaflytest.data.repository.exception.ExceptionSuperHeroComicsRepository
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.repository.SuperHeroComicsRepository
import com.jh.holaflytest.domain.useCase.GetSuperHeroComicsUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Module
@InstallIn(ViewModelComponent::class)
object SuperHeroComicModule {

    @Provides
    @ViewModelScoped
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

    @Named(EXCEPTION_SUPER_HERO_COMIC_REPOSITORY)
    @Provides
    @ViewModelScoped
    fun provideExceptionSuperHeroComicsRepository(): DomainExceptionRepository = ExceptionSuperHeroComicsRepository()

    @Provides
    @ViewModelScoped
    fun provideAlbumRepository(
        marvelApi: MarvelApi,
        @Named(EXCEPTION_SUPER_HERO_COMIC_REPOSITORY) exceptionSuperHeroComic: DomainExceptionRepository
    ): SuperHeroComicsRepository = SuperHeroComicsRepositoryImpl(marvelApi, exceptionSuperHeroComic)

    @Provides
    @ViewModelScoped
    fun provideAlbumUseCase(
        superHeroComicsRepository: SuperHeroComicsRepository
    ) = GetSuperHeroComicsUC(superHeroComicsRepository)
}

private const val EXCEPTION_SUPER_HERO_COMIC_REPOSITORY = "exceptionSuperHeroComicsRepository"
