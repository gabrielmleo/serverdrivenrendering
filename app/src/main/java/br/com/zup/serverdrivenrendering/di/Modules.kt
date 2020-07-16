package br.com.zup.serverdrivenrendering.di

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.data.manager.JsonManager
import br.com.zup.serverdrivenrendering.data.manager.LayoutDataSourceManager
import br.com.zup.serverdrivenrendering.domain.LayoutRepository
import br.com.zup.serverdrivenrendering.presentation.view.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single {
        LayoutRepository(
            layoutDataSource = get()
        )
    }
}

val dataModule = module {
    single<LayoutDataSource> {
        LayoutDataSourceManager(
            jsonProvider = get(),
            jsonManager = get()
        )
    }
    factory { JsonProvider() }
    factory { JsonManager() }
}

val presentationModule = module {
    viewModel {
        MainViewModel(
            layoutRepository = get()
        )
    }
}