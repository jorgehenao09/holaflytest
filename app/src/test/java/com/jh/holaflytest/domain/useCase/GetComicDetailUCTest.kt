package com.jh.holaflytest.domain.useCase

import com.jh.holaflytest.domain.exception.InternalErrorException
import com.jh.holaflytest.domain.model.comicDetail.ComicDetail
import com.jh.holaflytest.domain.repository.ComicDetailRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Jorge Henao on 15/11/23.
 */
class GetComicDetailUCTest {

    @MockK
    lateinit var comicDetailRepository: ComicDetailRepository

    private lateinit var getComicDetailUC: GetComicDetailUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getComicDetailUC = GetComicDetailUC(comicDetailRepository)
    }

    @Test
    fun whenGetComicDetailUCIsInvokedShouldReturnListComicsFlow() = runTest {
        val comicDetail = mockk<List<ComicDetail>>()
        val response: Flow<Result<List<ComicDetail>>> = flowOf(Result.success(comicDetail))

        coEvery { comicDetailRepository.getComicDetail("789") } returns response

        getComicDetailUC.invoke("789").collect {
            Assert.assertTrue(it.isSuccess)
        }

        coVerify(exactly = 1) { comicDetailRepository.getComicDetail("789") }
        confirmVerified(comicDetail)
    }

    @Test
    fun whenGetSuperHeroComicsUCIsInvokedShouldReturnDomainException() = runTest {
        val exception = InternalErrorException
        val response: Flow<Result<List<ComicDetail>>> = flowOf(Result.failure(exception))

        coEvery { comicDetailRepository.getComicDetail("123") } returns response

        getComicDetailUC.invoke("123").collect {
            Assert.assertTrue(it.isFailure)
        }

        coVerify(exactly = 1) { comicDetailRepository.getComicDetail("123") }
    }

    @After
    fun tearDown() {
        confirmVerified(comicDetailRepository)
    }
}
