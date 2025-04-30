package com.example.project.cache

import app.cash.sqldelight.db.SqlDriver
import io.exoquery.sql.TerpalDriver
import io.exoquery.sql.native.TerpalNativeDriver

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createTerpalDriver(): TerpalDriver {
        return TerpalNativeDriver.fromSchema(LaunchSchema, "launch.db")
    }
}
