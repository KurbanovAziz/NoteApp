plugins {
    id(Plugins.AGP.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.DaggerHilt.hilt)
}

android {
    namespace = "com.example.noteapp"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.noteapp"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.androidTestInstrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Deps.UI.androidCore)
    implementation(Deps.UI.appcompat)
    implementation(Deps.UI.material)
    implementation(Deps.UI.constraint)
    testImplementation(Deps.UI.junit)
    androidTestImplementation(Deps.UI.extJunit)
    androidTestImplementation(Deps.UI.espresso)

    //Room
    implementation(Deps.Room.room)
    implementation(Deps.Room.roomRuntime)
    kapt(Deps.Room.compiler)

    //Coroutines
    implementation(Deps.Coroutines.android)

    //Hilt
    implementation(Deps.DaggerHilt.hilt)
    kapt(Deps.DaggerHilt.compiler)

    // View Binding Delegate
    implementation(Deps.ViewBindingDelegate.viewBindingDelegate)

    //NavController
    implementation(Deps.NavComponent.fragment)
    implementation(Deps.NavComponent.ui)

    //Lifecycle
    implementation(Deps.Lifecycle.liveData)
    implementation(Deps.Lifecycle.viewModel)
    implementation(Deps.UI.fragment)

    // Module
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":di"))
}