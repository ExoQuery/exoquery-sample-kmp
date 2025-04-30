package com.example.project.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import com.example.project.RocketLaunch

class SpaceXApi {

  private val httpClient = HttpClient {
    install(ContentNegotiation) {
      json(Json {
        ignoreUnknownKeys = true
        useAlternativeNames = false
      })
    }
  }

  suspend fun getAllLaunches(): List<RocketLaunch> {
    return httpClient.get("https://api.spacexdata.com/v5/launches").body()
  }
}
