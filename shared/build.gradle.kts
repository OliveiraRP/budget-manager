plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.21"
    id("com.squareup.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

                // Sqldelight
                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
        }
    }
}

android {
    namespace = "com.oliveirarp.budgetmanager"
    compileSdk = 33
    defaultConfig {
        minSdk = 28
    }
}

sqldelight {
    database("BudgetManagerDatabase") {
        packageName = "com.oliveirarp.budgetmanager.database"
        sourceFolders = listOf("sqldelight")
    }
}