plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Agrego plugin de Google Services Gradle
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ulatina.proyectoprogramacionv"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ulatina.proyectoprogramacionv"
        minSdk = 23
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Agrego la dependencia de Firebase BoM (Bill of Materials)
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))


    //Agrego la dependencia de Google Analytics
    implementation(libs.firebase.analytics.ktx)

    //Agrego dependencia de Firebase Messaging
    implementation (libs.firebase.messaging)

    //Dependencias para autenticacion de google
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.auth)

    }