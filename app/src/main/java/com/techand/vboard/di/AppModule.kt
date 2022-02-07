package com.techand.vboard.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import com.techand.vboard.network.VideosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class AppModule {



    @Provides
    fun playlistApi(retrofit: Retrofit): VideosApi = retrofit.create(VideosApi::class.java)


    @Provides
    fun retrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://youtube-search-and-download.p.rapidapi.com/")
            // .baseUrl("http://192.168.1.100:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}