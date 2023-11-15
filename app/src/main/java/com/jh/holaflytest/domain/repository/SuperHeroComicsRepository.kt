package com.jh.holaflytest.domain.repository

import com.jh.holaflytest.domain.model.comics.Comic
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jorge Henao on 14/11/23.
 */
interface SuperHeroComicsRepository {

    fun getSuperHeroComics(characterId: Long): Flow<Result<List<Comic>>>
}
