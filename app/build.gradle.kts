apply(plugin = "com.android.application")
apply(plugin = "kotlin-android")
apply(plugin = "kotlin-android-extensions")

android {
    compileSdkVersion 29
    defaultConfig {
        applicationId "world.mitchmiller.bluessoundboard"
        minSdkVersion 21
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding true
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    kotlinOptions.useIR = true
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.cardview:cardview:1.0.0")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}
