import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.exoquery)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

   // listOf(
   //    iosX64(),
   //    iosArm64(),
   //    iosSimulatorArm64()
   //).forEach { iosTarget ->
   //    iosTarget.binaries.framework {
   //        baseName = "Shared"
   //        isStatic = false
   //    }
   //}

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.runtime)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation(libs.exoquery.runner.core)
        }
        androidMain {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.android.driver)
                // not sure why putting this here blows things up
                implementation(libs.jetbrains.annotations)
            }
        }
        //iosMain.dependencies {
        //    implementation(libs.ktor.client.darwin)
        //    implementation(libs.native.driver)
        //    implementation(libs.exoquery.runner.native)
        //    implementation(libs.jetbrains.annotations.kmp)
        //}
    }
}

fun MinimalExternalModuleDependency.simpleString() =
    this.let { "${it.module}:${it.versionConstraint.requiredVersion}" }

android {
    namespace = "com.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.project.cache")
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/service/local/repositories/releases/content/")
    mavenLocal()
}
