package com.example.project.cache

import com.example.project.Links
import com.example.project.Patch
import com.example.project.RocketLaunch
import io.exoquery.controller.transaction
import io.exoquery.runOn

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = databaseDriverFactory.createTerpalDriver()

    internal suspend fun getAllLaunches(): List<RocketLaunch> =
        DAO.selectAllLaunchesInfo().runOn(database)

    private fun mapLaunchSelecting(
        flightNumber: Long,
        missionName: String,
        details: String?,
        launchSuccess: Boolean?,
        launchDateUTC: String,
        patchUrlSmall: String?,
        patchUrlLarge: String?,
        articleUrl: String?
    ): RocketLaunch {
        return RocketLaunch(
            flightNumber = flightNumber.toInt(),
            missionName = missionName,
            details = details,
            launchDateUTC = launchDateUTC,
            launchSuccess = launchSuccess,
            links = Links(
                patch = Patch(
                    small = patchUrlSmall,
                    large = patchUrlLarge
                ),
                article = articleUrl
            )
        )
    }

    internal suspend fun clearAndCreateLaunches(launches: List<RocketLaunch>) {
        database.transaction {
            DAO.removeAllLaunches().runOnTransaction()
            launches.forEach { launch ->
                DAO.insertLaunch(launch).runOnTransaction()
            }
        }
    }
}
