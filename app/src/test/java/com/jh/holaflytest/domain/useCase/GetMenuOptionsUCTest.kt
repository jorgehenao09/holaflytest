package com.jh.holaflytest.domain.useCase

import com.jh.holaflytest.domain.exception.InternalErrorException
import com.jh.holaflytest.domain.model.MenuOptions
import com.jh.holaflytest.domain.repository.MenuOptionsRepository
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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Jorge Henao on 13/11/23.
 */
class GetMenuOptionsUCTest {

    @MockK
    lateinit var menuOptionsRepository: MenuOptionsRepository

    private lateinit var getMenuOptionsUC: GetMenuOptionsUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMenuOptionsUC = GetMenuOptionsUC(menuOptionsRepository)
    }

    @Test
    fun whenGetMenuOptionsUCIsInvokedShouldReturnMenuOptionsFlow() = runTest {
        val menuOptions = mockk<MenuOptions>()
        val response: Flow<Result<MenuOptions>> = flowOf(Result.success(menuOptions))

        coEvery { menuOptionsRepository.getMenuOptions() } returns response

        getMenuOptionsUC.invoke().collect {
            assertTrue(it.isSuccess)
        }

        coVerify(exactly = 1) { menuOptionsRepository.getMenuOptions() }
        confirmVerified(menuOptions)
    }

    @Test
    fun whenGetMenuOptionsUCIsInvokedShouldReturnDomainException() = runTest {
        val exception = InternalErrorException
        val response: Flow<Result<MenuOptions>> = flowOf(Result.failure(exception))

        coEvery { menuOptionsRepository.getMenuOptions() } returns response

        getMenuOptionsUC.invoke().collect {
            assertTrue(it.isFailure)
        }

        coVerify(exactly = 1) { menuOptionsRepository.getMenuOptions() }
    }

    @After
    fun tearDown() {
        confirmVerified(menuOptionsRepository)
    }
}
