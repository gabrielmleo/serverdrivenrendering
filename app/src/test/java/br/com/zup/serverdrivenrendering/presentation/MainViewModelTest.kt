package br.com.zup.serverdrivenrendering.presentation

import br.com.zup.serverdrivenrendering.InstantExecutorExtension
import br.com.zup.serverdrivenrendering.domain.LayoutService
import br.com.zup.serverdrivenrendering.domain.util.DefaultScreenProvider
import br.com.zup.serverdrivenrendering.model.Response
import br.com.zup.serverdrivenrendering.model.ScreenInfo
import br.com.zup.serverdrivenrendering.presentation.view.MainViewModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@Suppress("ClassName", "TestFunctionName")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(InstantExecutorExtension::class)
class MainViewModelTest {

    private val defaultScreenProviderMock: DefaultScreenProvider = mockk()
    private val layoutServiceMock: LayoutService = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    private val subject = MainViewModel(
        layoutRepository = layoutServiceMock,
        dispatcher = testDispatcher,
        defaultScreenProvider = defaultScreenProviderMock
    )

    @Nested
    inner class `Given a layoutRepository returning success info layout` {

        @Test
        fun `When call fetchScreenLayout the ViewModel Should call repository get method`() =
            runBlocking {
                val screenInfoMock = mockk<ScreenInfo>()
                coEvery { layoutServiceMock.getMainScreenLayoutData() } returns Response.Success(
                    screenInfo = screenInfoMock
                )
                subject.fetchScreenLayout()
                coVerify(exactly = 1) { layoutServiceMock.getMainScreenLayoutData() }
            }

        @Test
        fun `Live data Object Should have success object inside`() = runBlocking {
            val screenInfoMock = mockk<ScreenInfo>()
            coEvery { layoutServiceMock.getMainScreenLayoutData() } returns Response.Success(
                screenInfo = screenInfoMock
            )
            subject.fetchScreenLayout()
            Assertions.assertEquals(screenInfoMock, subject.mainScreenLayoutLiveData.value)
        }
    }

    @Nested
    inner class `Given a layoutRepository returning failure info layout` {

        private val defaultScreenMock: ScreenInfo = mockk()

        @Test
        fun `Should call default provider method to get layout`() = runBlocking {
            val failureResponseMock = mockk<Response.Failure>()
            every { defaultScreenProviderMock.getDefaultErrorScreen() } returns defaultScreenMock
            coEvery { layoutServiceMock.getMainScreenLayoutData() } returns failureResponseMock
            subject.fetchScreenLayout()
            verify(exactly = 1) { defaultScreenProviderMock.getDefaultErrorScreen() }
        }

        @Test
        fun `Live data Object Should have failure object inside`() = runBlocking {
            val failureResponseMock = mockk<Response.Failure>()
            every { defaultScreenProviderMock.getDefaultErrorScreen() } returns defaultScreenMock
            coEvery { layoutServiceMock.getMainScreenLayoutData() } returns failureResponseMock
            subject.fetchScreenLayout()
            Assertions.assertEquals(defaultScreenMock, subject.mainScreenLayoutLiveData.value)
        }
    }
}