plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

// OAuth-приложение DEP ID: -PDEP_CLIENT_ID=... или переменные окружения (секреты CI).
fun depProp(name: String) = (findProperty(name) as String?) ?: System.getenv(name) ?: ""

android {
    namespace = "com.bablo.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bablo.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 74
        versionName = "6.7.7"

        buildConfigField("String", "DEP_CLIENT_ID", "\"${depProp("DEP_CLIENT_ID")}\"")
        buildConfigField("String", "DEP_CLIENT_SECRET", "\"${depProp("DEP_CLIENT_SECRET")}\"")
    }

    signingConfigs {
        create("bablo") {
            storeFile = file("bablo.jks")
            storePassword = "bablo67"
            keyAlias = "bablo"
            keyPassword = "bablo67"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            signingConfig = signingConfigs.getByName("bablo")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
}
