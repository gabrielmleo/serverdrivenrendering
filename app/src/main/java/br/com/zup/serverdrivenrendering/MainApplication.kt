package br.com.zup.serverdrivenrendering

import android.app.Application
import br.com.zup.serverdrivenrendering.di.dataModule
import br.com.zup.serverdrivenrendering.di.presentationModule
import br.com.zup.serverdrivenrendering.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    presentationModule
                )
            )
        }
    }
}