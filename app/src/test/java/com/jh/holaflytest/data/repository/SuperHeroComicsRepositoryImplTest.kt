package com.jh.holaflytest.data.repository

import com.jh.holaflytest.data.api.MarvelApi
import com.jh.holaflytest.data.api.model.MarvelResponse
import com.jh.holaflytest.data.api.model.Paginated
import com.jh.holaflytest.data.api.model.comics.ComicDTO
import com.jh.holaflytest.domain.exception.InternalErrorException
import com.jh.holaflytest.domain.model.comics.Comic
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.repository.SuperHeroComicsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.ConnectException

/**
 * Created by Jorge Henao on 14/11/23.
 */
class SuperHeroComicsRepositoryImplTest {

    @MockK
    lateinit var marvelApi: MarvelApi

    @MockK
    lateinit var domainExceptionRepository: DomainExceptionRepository

    private lateinit var superHeroComicsRepository: SuperHeroComicsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        superHeroComicsRepository =
            SuperHeroComicsRepositoryImpl(marvelApi, domainExceptionRepository)
    }

    @Test
    fun whenGetSuperHeroComicsIsCalledShouldReturnComicListFlow() = runTest {
        val superHeroComics = mockk<MarvelResponse<ComicDTO>>()
        val comicsData = mockk<Paginated<ComicDTO>>()
        val comicDTO = mockk<ComicDTO>(relaxed = true)
        val comicsList = listOf(comicDTO)
        val comic = mockk<Comic>(relaxed = true)

        coEvery { marvelApi.getSuperHeroComics(123) } returns superHeroComics
        every { superHeroComics.data } returns comicsData
        every { comicsData.results } returns comicsList
        every { comicDTO.toDomainComic() } returns comic

        superHeroComicsRepository.getSuperHeroComics(123)
            .collect { result ->
                Assert.assertTrue(result.isSuccess)
            }

        coVerify(exactly = 1) { marvelApi.getSuperHeroComics(123) }
        verify { superHeroComics.data }
        verify { comicsData.results }
        verify { comicDTO.toDomainComic() }
        confirmVerified(superHeroComics, comicsData, comicDTO, comic)
    }

    @Test
    fun whenGetSuperHeroComicsIsCalledShouldReturnException() = runTest {
        val exception = ConnectException()
        coEvery { marvelApi.getSuperHeroComics(456) } throws exception
        every { domainExceptionRepository.manageError(exception) } returns InternalErrorException

        superHeroComicsRepository.getSuperHeroComics(456)
            .collect { result ->
                Assert.assertTrue(result.isFailure)
            }

        coVerify(exactly = 1) { marvelApi.getSuperHeroComics(456) }
        coVerify { domainExceptionRepository.manageError(exception) }
    }

    @After
    fun tearDown() {
        confirmVerified(marvelApi, domainExceptionRepository)
    }
}
