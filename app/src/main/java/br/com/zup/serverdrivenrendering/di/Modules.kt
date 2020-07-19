package br.com.zup.serverdrivenrendering.di

import br.com.zup.serverdrivenrendering.data.datasource.JsonProvider
import br.com.zup.serverdrivenrendering.data.datasource.LayoutDataSource
import br.com.zup.serverdrivenrendering.data.manager.JsonHandler
import br.com.zup.serverdrivenrendering.data.manager.LayoutDataManager
import br.com.zup.serverdrivenrendering.domain.LayoutService
import br.com.zup.serverdrivenrendering.domain.Repository
import br.com.zup.serverdrivenrendering.domain.util.DefaultScreenProvider
import br.com.zup.serverdrivenrendering.presentation.render.ScreenInfoRenderer
import br.com.zup.serverdrivenrendering.presentation.view.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModule = module {
    single {
        Repository(
            layoutDataSource = get()
        )
    }
    factory { LayoutService(repository = get()) }
}

val dataModule = module {
    single<LayoutDataSource> {
        LayoutDataManager(
            jsonProvider = get(),
            jsonHandler = get()
        )
    }
    factory { JsonProvider() }
    factory { JsonHandler() }
}

val presentationModule = module {
    viewModel {
        MainViewModel(
            layoutService = get(),
            defaultScreenProvider = get()
        )
    }

    factory { DefaultScreenProvider() }
    factory { ScreenInfoRenderer(androidContext()) }
}