package br.com.zup.serverdrivenrendering.domain

import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.domain.model.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@Suppress("ClassName", "TestFunctionName")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LayoutRepositoryTest {

    @Nested
    inner class `Given a layoutDataManager` {

        private val responseMock: Response = mockk()
        private val layoutDataSourceMock: LayoutDataSource = mockk()
        private val subject = LayoutRepository(layoutDataSourceMock)

        @Test
        fun `When call getMainScreenLayoutData Should call layoutDataManager method`() =
            runBlocking {
                coEvery { layoutDataSourceMock.getMainScreenLayoutData() } returns responseMock
                subject.getMainScreenLayoutData()
                coVerify { layoutDataSourceMock.getMainScreenLayoutData() }
            }
    }
}