package com.example.project.cache

import android.content.Context
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.exoquery.controller.android.AndroidDatabaseController

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createTerpalDriver(): AndroidDatabaseController =
      AndroidDatabaseController.fromApplicationContext("launch.db", context, LaunchSchema)
}
