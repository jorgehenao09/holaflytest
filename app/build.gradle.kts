plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jh.holaflytest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jh.holaflytest"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        if (project.hasProperty("MARVEL_PUBLIC_API_KEY")) {
            val marvelPublicKey = '"' + project.property("MARVEL_PUBLIC_API_KEY") as String + '"'
            val marvelPrivateKey = '"' + project.property("MARVEL_PRIVATE_API_KEY") as String + '"'
            val marvelBaseUrl = '"' + project.property("MARVEL_BASE_URL") as String + '"'
            buildConfigField("String", "MARVEL_PUBLIC_API_KEY", marvelPublicKey)
            buildConfigField("String", "MARVEL_PRIVATE_API_KEY", marvelPrivateKey)
            buildConfigField("String", "MARVEL_BASE_URL", marvelBaseUrl)
        } else {
            throw GradleException("Please provide the Marvel API keys as gradle properties")
        }

        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.compose.material3)

    implementation(libs.retrofit2.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.converter.gson)

    implementation(libs.com.google.dagger)
    implementation(libs.androidx.hilt)
    kapt(libs.com.google.dagger.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.io.mockk)
    testImplementation(libs.org.jetbrains.kotlinx)
    testImplementation(libs.org.jetbrains.kotlinx.test)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
    debugImplementation(libs.androidx.compose.ui.test.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
