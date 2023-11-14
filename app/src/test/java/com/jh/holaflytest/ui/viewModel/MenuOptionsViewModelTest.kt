package com.jh.holaflytest.ui.viewModel

import app.cash.turbine.test
import com.jh.holaflytest.MainDispatcherRule
import com.jh.holaflytest.domain.model.MenuOptions
import com.jh.holaflytest.domain.useCase.GetMenuOptionsUC
import com.jh.holaflytest.ui.state.MenuOptionsStateUI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Jorge Henao on 13/11/23.
 */
class MenuOptionsViewModelTest {

    private val unconfinedDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(unconfinedDispatcher)
    @MockK
    lateinit var getMenuOptionsUC: GetMenuOptionsUC

    private lateinit var menuOptionsViewModel: MenuOptionsViewModel
    private val _menuOptionsStateUI = mockk<MutableStateFlow<MenuOptionsStateUI>>(relaxed = true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        menuOptionsViewModel = MenuOptionsViewModel(getMenuOptionsUC, _menuOptionsStateUI, unconfinedDispatcher)
    }

    @Test
    fun whenGetMenuOptionsIsCalledShouldReturnMenuOptionsSuccess() = runTest(unconfinedDispatcher) {
        val menuOptions = mockk<MenuOptions>()
        val response: Flow<Result<MenuOptions>> = flowOf(Result.success(menuOptions))

        coEvery { getMenuOptionsUC.invoke() } returns response

        menuOptionsViewModel.menuOptionsSate.test {
            menuOptionsViewModel.getMenuOptions()

            assertEquals(MenuOptionsStateUI(isLoading = false), awaitItem())
            assertEquals(MenuOptionsStateUI(isLoading = true), awaitItem())
            assertEquals(MenuOptionsStateUI(isLoading = false, isSuccess = menuOptions), awaitItem())
        }

        coVerify(exactly = 1) { getMenuOptionsUC.invoke() }
        confirmVerified(menuOptions)
    }

    @After
    fun tearDown() {
        confirmVerified(getMenuOptionsUC)
    }
}
