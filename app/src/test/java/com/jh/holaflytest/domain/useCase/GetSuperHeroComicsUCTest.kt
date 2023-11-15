package com.jh.holaflytest.domain.useCase

import com.jh.holaflytest.domain.exception.InternalErrorException
import com.jh.holaflytest.domain.model.comics.Comic
import com.jh.holaflytest.domain.repository.SuperHeroComicsRepository
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
 * Created by Jorge Henao on 14/11/23.
 */
class GetSuperHeroComicsUCTest {

    @MockK
    lateinit var superHeroComicsRepository: SuperHeroComicsRepository

    private lateinit var getSuperHeroComicsUC: GetSuperHeroComicsUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getSuperHeroComicsUC = GetSuperHeroComicsUC(superHeroComicsRepository)
    }

    @Test
    fun whenGetSuperHeroComicsUCIsInvokedShouldReturnListComicsFlow() = runTest {
        val comics = mockk<List<Comic>>()
        val response: Flow<Result<List<Comic>>> = flowOf(Result.success(comics))

        coEvery { superHeroComicsRepository.getSuperHeroComics(789) } returns response

        getSuperHeroComicsUC.invoke(789).collect {
            Assert.assertTrue(it.isSuccess)
        }

        coVerify(exactly = 1) { superHeroComicsRepository.getSuperHeroComics(789) }
        confirmVerified(comics)
    }

    @Test
    fun whenGetSuperHeroComicsUCIsInvokedShouldReturnDomainException() = runTest {
        val exception = InternalErrorException
        val response: Flow<Result<List<Comic>>> = flowOf(Result.failure(exception))

        coEvery { superHeroComicsRepository.getSuperHeroComics(123) } returns response

        getSuperHeroComicsUC.invoke(123).collect {
            Assert.assertTrue(it.isFailure)
        }

        coVerify(exactly = 1) { superHeroComicsRepository.getSuperHeroComics(123) }
    }

    @After
    fun tearDown() {
        confirmVerified(superHeroComicsRepository)
    }
}
