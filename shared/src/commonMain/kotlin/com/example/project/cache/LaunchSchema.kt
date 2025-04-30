package com.example.project.cache

import io.exoquery.SqlAction
import io.exoquery.capture
import io.exoquery.controller.Controller
import io.exoquery.controller.runActions
import io.exoquery.controller.runOn
import io.exoquery.controller.sqlite.CallAfterVersion
import io.exoquery.controller.sqlite.TerpalSchema
import io.exoquery.runOn

object LaunchSchema: TerpalSchema<Unit> {
    override val version: Long
        get() = 1

    override suspend fun create(driver: Controller<*>) {
       driver.runActions(
         """
            CREATE TABLE Launch (
                flightNumber INTEGER NOT NULL,
                missionName TEXT NOT NULL,
                details TEXT,
                launchSuccess INTEGER DEFAULT NULL,
                launchDateUTC TEXT NOT NULL,
                patchUrlSmall TEXT,
                patchUrlLarge TEXT,
                articleUrl TEXT
            )
          """
       )
    }

    override suspend fun migrate(driver: Controller<*>, oldVersion: Long, newVersion: Long, vararg callbacks: CallAfterVersion) {
        // No-op
    }
}
