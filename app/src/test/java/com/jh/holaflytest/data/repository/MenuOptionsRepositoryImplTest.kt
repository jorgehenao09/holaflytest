package com.jh.holaflytest.data.repository

import com.jh.holaflytest.data.localStorage.localFile.MenuFileManager
import com.jh.holaflytest.data.utils.getMenuOptionsResponse
import com.jh.holaflytest.domain.exception.InternalErrorException
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import com.jh.holaflytest.domain.repository.MenuOptionsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.ConnectException

/**
 * Created by Jorge Henao on 13/11/23.
 */
class MenuOptionsRepositoryImplTest {

    @MockK
    lateinit var menuFileManager: MenuFileManager
    @MockK
    lateinit var domainExceptionRepository: DomainExceptionRepository

    private lateinit var menuOptionsRepository: MenuOptionsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        menuOptionsRepository = MenuOptionsRepositoryImpl(menuFileManager, domainExceptionRepository)
    }

    @Test
    fun whenGetMenuOptionsIsCalledShouldReturnMenuOptionsFlow() = runTest {
        coEvery { menuFileManager.getMenuOptions() } returns getMenuOptionsResponse()

        menuOptionsRepository.getMenuOptions()
            .collect { result ->
                assertTrue(result.isSuccess)
        }

        coVerify(exactly = 1) { menuFileManager.getMenuOptions() }
    }

    @Test
    fun whenGetMenuOptionsIsCalledShouldReturnException() = runTest {
        val exception = ConnectException()
        coEvery { menuFileManager.getMenuOptions() } throws exception
        every { domainExceptionRepository.manageError(exception) } returns InternalErrorException

        menuOptionsRepository.getMenuOptions()
            .collect { result ->
                assertTrue(result.isFailure)
            }

        coVerify(exactly = 1) { menuFileManager.getMenuOptions() }
        coVerify { domainExceptionRepository.manageError(exception) }
    }

    @After
    fun tearDown() {
        confirmVerified(menuFileManager, domainExceptionRepository)
    }
}
