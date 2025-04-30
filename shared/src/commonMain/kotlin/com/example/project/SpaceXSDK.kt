package com.example.project

import com.example.project.cache.Database
import com.example.project.cache.DatabaseDriverFactory
import com.example.project.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory, val api: SpaceXApi) {
  private val database = Database(databaseDriverFactory)

  @Throws(Exception::class)
  suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
    val cachedLaunches = database.getAllLaunches()
    return if (cachedLaunches.isNotEmpty() && !forceReload) {
      cachedLaunches
    } else {
      api.getAllLaunches().also {
        database.clearAndCreateLaunches(it)
      }
    }
  }
}
