package com.example.project.cache

import app.cash.sqldelight.db.SqlDriver
import io.exoquery.controller.Controller
import io.exoquery.controller.ControllerTransactional
import io.exoquery.controller.native.NativeDatabaseController

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createTerpalDriver(): ControllerTransactional<*, *, *> {
        return NativeDatabaseController.fromSchema(LaunchSchema, "launch.db")
    }
}
