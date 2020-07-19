package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.model.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@Suppress("ClassName", "TestFunctionName")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LayoutServiceTest {

    @Nested
    inner class `Given a layoutDataManager` {

        private val responseMock: Response = mockk()
        private val repositoryMock: Repository = mockk()
        private val subject = LayoutService(repository = repositoryMock)

        @Test
        fun `When call getMainScreenLayoutData Should call layoutDataManager method`() =
            runBlocking {
                coEvery { repositoryMock.getMainScreenLayoutData() } returns responseMock
                subject.getMainScreenLayoutData()
                coVerify { repositoryMock.getMainScreenLayoutData() }
            }
    }
}