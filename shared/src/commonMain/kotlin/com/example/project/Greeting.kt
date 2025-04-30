package com.example.project

class Greeting {
    private val platform = com.example.project.getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}