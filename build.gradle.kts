plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false

    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false

}
