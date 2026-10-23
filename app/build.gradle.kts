plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.plugin.compose")
}

val material3: String = providers.gradleProperty("material3").get()

android {
  namespace = "dev.repro.sheetpager"
  compileSdk = 37

  defaultConfig {
    applicationId = "dev.repro.sheetpager"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"
    buildConfigField("String", "MATERIAL3_VERSION", "\"$material3\"")
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }
}

dependencies {
  implementation("androidx.activity:activity-compose:1.11.0")
  implementation("androidx.compose.material3:material3:$material3")
  // Same foundation in every build, so only Material3 changes between them.
  implementation("androidx.compose.foundation:foundation:1.12.1")
}
