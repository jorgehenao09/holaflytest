package com.jh.holaflytest.data.repository

import com.jh.holaflytest.data.api.MarvelApi
import com.jh.holaflytest.data.api.model.MarvelResponse
import com.jh.holaflytest.data.api.model.Paginated
import com.jh.holaflytest.data.api.model.comicDetail.ComicDetailDTO
import com.jh.holaflytest.domain.exception.InternalErrorException
import com.jh.holaflytest.domain.model.comicDetail.ComicDetail
import com.jh.holaflytest.domain.repository.ComicDetailRepository
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
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
 * Created by Jorge Henao on 15/11/23.
 */
class ComicDetailRepositoryImplTest {

    @MockK
    lateinit var marvelApi: MarvelApi

    @MockK
    lateinit var domainExceptionRepository: DomainExceptionRepository

    private lateinit var comicDetailRepository: ComicDetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        comicDetailRepository =
            ComicDetailRepositoryImpl(marvelApi, domainExceptionRepository)
    }

    @Test
    fun whenGetComicDetailIsCalledShouldReturnComicListFlow() = runTest {
        val comicDetails = mockk<MarvelResponse<ComicDetailDTO>>()
        val comicData = mockk<Paginated<ComicDetailDTO>>()
        val comicDetailDTO = mockk<ComicDetailDTO>(relaxed = true)
        val comicDetailList = listOf(comicDetailDTO)
        val comicDetail = mockk<ComicDetail>(relaxed = true)

        coEvery { marvelApi.getComicDetails("123") } returns comicDetails
        every { comicDetails.data } returns comicData
        every { comicData.results } returns comicDetailList
        every { comicDetailDTO.toDomainComicDetail() } returns comicDetail

        comicDetailRepository.getComicDetail("123")
            .collect { result ->
                Assert.assertTrue(result.isSuccess)
            }

        coVerify(exactly = 1) { marvelApi.getComicDetails("123") }
        verify { comicDetails.data }
        verify { comicData.results }
        verify { comicDetailDTO.toDomainComicDetail() }
        confirmVerified(comicDetails, comicData, comicDetailDTO, comicDetail)
    }

    @Test
    fun whenGetSuperHeroComicsIsCalledShouldReturnException() = runTest {
        val exception = ConnectException()
        coEvery { marvelApi.getComicDetails("456") } throws exception
        every { domainExceptionRepository.manageError(exception) } returns InternalErrorException

        comicDetailRepository.getComicDetail("456")
            .collect { result ->
                Assert.assertTrue(result.isFailure)
            }

        coVerify(exactly = 1) { marvelApi.getComicDetails("456") }
        coVerify { domainExceptionRepository.manageError(exception) }
    }

    @After
    fun tearDown() {
        confirmVerified(marvelApi, domainExceptionRepository)
    }
}
