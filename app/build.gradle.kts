plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kover.coverage)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.avsoftware.integertoromannumerals"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.avsoftware.integertoromannumerals"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
    packaging {
        resources.excludes += setOf(
            "/META-INF/LICENSE",
            "/META-INF/LICENSE.md",
            "/META-INF/LICENSE.txt",
            "/META-INF/NOTICE",
            "/META-INF/NOTICE.md",
            "/META-INF/NOTICE.txt",
            "/META-INF/LICENSE-notice.md"
        )
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Orbit
    implementation(libs.orbit.core)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.compose)

    // Timber
    implementation(libs.timber)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Immutable Collections
    implementation(libs.immutable.collections)

    // Unit Tests (JUnit 5 via Kotest)
    testImplementation(libs.kotest.runner.junit5)
    // Remove JUnit 4 for unit tests to avoid confusion
    // testImplementation(libs.junit) // Commented out, as Kotest uses JUnit 5

    // Android Instrumentation Tests (JUnit 4 for Compose)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test.accessibility)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.junit) 
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.mockk.android) {
        exclude(group = "org.junit")
    }

    // Debug tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}