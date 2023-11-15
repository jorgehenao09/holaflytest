package com.jh.holaflytest.domain.useCase

import com.jh.holaflytest.domain.model.comics.Comic
import com.jh.holaflytest.domain.repository.SuperHeroComicsRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Jorge Henao on 14/11/23.
 */
class GetSuperHeroComicsUC(
    private val superHeroComicsRepository: SuperHeroComicsRepository
) {

    operator fun invoke(characterId: Long) = flow<Result<List<Comic>>> {
        superHeroComicsRepository.getSuperHeroComics(characterId).collect { superHeroComicsResult ->
            superHeroComicsResult.fold(
                onSuccess = { superHeroComics ->
                    emit(Result.success(superHeroComics))
                },
                onFailure = { domainException ->
                    emit(Result.failure(domainException))
                }
            )
        }
    }
}
