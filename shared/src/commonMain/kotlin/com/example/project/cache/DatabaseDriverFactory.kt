package com.example.project.cache

import io.exoquery.controller.Controller

import app.cash.sqldelight.db.SqlDriver
import io.exoquery.controller.ControllerTransactional

interface DatabaseDriverFactory {
  fun createTerpalDriver(): ControllerTransactional<*, *, *>
}
