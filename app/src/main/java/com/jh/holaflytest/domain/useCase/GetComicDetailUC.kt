package com.jh.holaflytest.domain.useCase

import com.jh.holaflytest.domain.model.comicDetail.ComicDetail
import com.jh.holaflytest.domain.repository.ComicDetailRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Jorge Henao on 15/11/23.
 */
class GetComicDetailUC(
    private val comicDetailRepository: ComicDetailRepository
) {
    operator fun invoke(comicId: String) = flow<Result<List<ComicDetail>>> {
        comicDetailRepository.getComicDetail(comicId).collect { comicDetailResult ->
            comicDetailResult.fold(
                onSuccess = { comicDetail ->
                    emit(Result.success(comicDetail))
                },
                onFailure = { domainException ->
                    emit(Result.failure(domainException))
                }
            )
        }
    }
}
