package com.techand.vboard.di

import com.techand.vboard.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(FragmentComponent::class)
class AppModule {


    @Provides
    fun playlistApi(retrofit: Retrofit): VideosApi = retrofit.create(VideosApi::class.java)


    @Provides
    fun retrofit(): Retrofit {
        val client = OkHttpClient.Builder().apply {
            addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header(apikey, apikeyValue)
                builder.header(apiHost, apiHostValue)
                return@Interceptor chain.proceed(builder.build())
            })
        }.build()
        return Retrofit.Builder()
            .baseUrl("https://youtube-search-and-download.p.rapidapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}