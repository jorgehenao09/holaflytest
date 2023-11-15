package com.jh.holaflytest.data.repository

import com.jh.holaflytest.data.api.MarvelApi
import com.jh.holaflytest.domain.model.comics.Comic
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.repository.SuperHeroComicsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Jorge Henao on 14/11/23.
 */
class SuperHeroComicsRepositoryImpl(
    private val marvelApi: MarvelApi,
    private val domainExceptionRepository: DomainExceptionRepository
) : SuperHeroComicsRepository {

    override fun getSuperHeroComics(): Flow<Result<List<Comic>>> =
        flow {
            val comicResponse = marvelApi.getSuperHeroComics()
            val comics = comicResponse.data.results.map { it.toDomainComic() }
            emit(Result.success(comics))
        }.catch {
            emit(Result.failure(domainExceptionRepository.manageError(it)))
        }
}
