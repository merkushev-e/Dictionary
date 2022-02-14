import org.gradle.api.JavaVersion

object Config {
    const val application_id = "ru.gb.dictionary"
    const val compile_sdk = 31
    const val min_sdk = 24
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val historyscreen = ":historyScreen"
}

object Versions {
    //Design
    const val appcompat = "1.4.1"
    const val material = "1.0.0"

    //Kotlin
    const val core = "1.7.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.6.0'"
    const val coroutinesAndroid = "1.6.0"


    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.3"
    const val adapterCoroutines = "0.9.2"
    const val adapterRxJava = "2.9.0"


    //Koin
    const val koinAndroid = "2.1.6"
    const val koinViewModel = "2.1.6"


    //Room
    const val roomKtx = "2.4.1"
    const val runtime = "2.4.1"
    const val roomCompiler = "2.4.1"

    //Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.4.0"
    //Picasso
    const val picassoversion = "2.71828"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Picasso{
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoversion}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"


}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val retrofit_adapter_rxjava3= "com.squareup.retrofit2:adapter-rxjava3:${Versions.adapterRxJava}"


}

object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
}



object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
