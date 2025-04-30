package com.example.project

import com.example.project.SpaceXSDK
import org.koin.core.component.KoinComponent
import com.example.project.RocketLaunch
import org.koin.core.component.inject
import com.example.project.cache.IOSDatabaseDriverFactory
import com.example.project.network.SpaceXApi
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinHelper : KoinComponent {
    private val sdk: SpaceXSDK by inject<SpaceXSDK>()

    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        return sdk.getLaunches(forceReload = forceReload)
    }
}

fun initKoin() {
    startKoin {
        modules(module {
            single<SpaceXApi> { SpaceXApi() }
            single<SpaceXSDK> {
                SpaceXSDK(
                    databaseDriverFactory = IOSDatabaseDriverFactory(), api = get()
                )
            }
        })
    }
}
