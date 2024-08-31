import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {

        androidMain.dependencies {

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.preview)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.appcompat)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.android)

            implementation(libs.arkivanov.decompose)
            implementation(libs.arkivanov.decompose.extensions.compose)
            implementation(libs.arkivanov.mvikotlin)
            implementation(libs.arkivanov.mvikotlin.main)
            implementation(libs.arkivanov.mvikotlin.logging)
            implementation(libs.arkivanov.mvikotlin.timetravel)
            implementation(libs.arkivanov.mvikotlin.extensions.reaktive)
            implementation(libs.arkivanov.essenty.lifecycle)

            implementation(libs.coil.compose)
            implementation(libs.coil.svg)
        }

        commonMain.dependencies {

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.preview)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.appcompat)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.android)

            implementation(libs.arkivanov.decompose)
            implementation(libs.arkivanov.decompose.extensions.compose)
            implementation(libs.arkivanov.mvikotlin)
            implementation(libs.arkivanov.mvikotlin.main)
            implementation(libs.arkivanov.mvikotlin.logging)
            implementation(libs.arkivanov.mvikotlin.timetravel)
            implementation(libs.arkivanov.mvikotlin.extensions.reaktive)
            implementation(libs.arkivanov.essenty.lifecycle)

            implementation(libs.coil.compose)
            implementation(libs.coil.svg)

        }
    }
}

android {
    namespace = "com.yusufnasserdev.images"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.yusufnasserdev.images"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

