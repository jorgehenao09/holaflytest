package com.jh.holaflytest.domain.repository

import com.jh.holaflytest.domain.model.comicDetail.ComicDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jorge Henao on 15/11/23.
 */
interface ComicDetailRepository {

    fun getComicDetail(comicId: String): Flow<Result<List<ComicDetail>>>
}
