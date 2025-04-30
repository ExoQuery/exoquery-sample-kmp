package com.example.project

interface Platform {
    val name: String
}

expect fun getPlatform(): com.example.project.Platform
