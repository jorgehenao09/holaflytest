package com.jh.holaflytest.data.repository

import com.jh.holaflytest.data.api.MarvelApi
import com.jh.holaflytest.domain.model.comicDetail.ComicDetail
import com.jh.holaflytest.domain.repository.ComicDetailRepository
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Jorge Henao on 15/11/23.
 */
class ComicDetailRepositoryImpl(
    private val marvelApi: MarvelApi,
    private val domainExceptionRepository: DomainExceptionRepository
): ComicDetailRepository {

    override fun getComicDetail(comicId: String): Flow<Result<List<ComicDetail>>> =
        flow {
            val comicDetailResponse = marvelApi.getComicDetails(comicId)
            val comics = comicDetailResponse.data.results.map { it.toDomainComicDetail() }
            emit(Result.success(comics))
        }.catch {
            emit(Result.failure(domainExceptionRepository.manageError(it)))
        }
}
