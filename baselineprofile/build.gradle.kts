import com.toquete.boxbox.plugins.configureFlavors

plugins {
    alias(libs.plugins.android.test)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.toquete.boxbox.baselineprofile"
    compileSdk = 36

    defaultConfig {
        minSdk = 28
        targetSdk = 36
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    configureFlavors(this)

    targetProjectPath = ":app"

    testOptions.managedDevices.localDevices {
        create("pixel6Api33") {
            device = "Pixel 6"
            apiLevel = 33
            systemImageSource = "aosp"
        }
    }
}

baselineProfile {
    managedDevices.clear()
    managedDevices += "pixel6Api33"
    useConnectedDevices = false
}

dependencies {
    implementation(libs.junit.ext)
    implementation(libs.espresso)
    implementation(libs.uiautomator)
    implementation(libs.benchmark.macro)
}
