plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.bruno.universitieslistapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bruno.universitieslistapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.okhttpLogging)
    implementation(libs.rxjavaRetrofitAdapter)
    implementation(libs.timber)
    implementation(libs.moshi)
    implementation(libs.moshiKotlin)
    implementation(libs.moshiConverter)
    implementation(libs.room)
    implementation(libs.roomKtx)
    implementation(libs.roomRxjava)
    implementation(libs.koin)
    implementation(project(":network-universities"))
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}