package com.example.project.cache

import com.example.project.RocketLaunch
import io.exoquery.SqlCompiledQuery
import io.exoquery.capture

object DAO {
    fun selectAllLaunchesInfo(): SqlCompiledQuery<RocketLaunch> =
      capture {
        Table<RocketLaunch>()
      }.buildFor.Sqlite()

    fun removeAllLaunches() =
      capture {
        delete<RocketLaunch>().all()
      }.buildFor.Sqlite()

    fun insertLaunch(rl: RocketLaunch) =
      capture {
        insert<RocketLaunch> { setParams(rl) }
      }.buildFor.Sqlite()
}
