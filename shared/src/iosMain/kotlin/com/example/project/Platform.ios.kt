package com.example.project

import platform.UIKit.UIDevice

class IOSPlatform: com.example.project.Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): com.example.project.Platform = com.example.project.IOSPlatform()
