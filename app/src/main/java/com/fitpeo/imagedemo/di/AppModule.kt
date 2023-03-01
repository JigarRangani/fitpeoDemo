package com.fitpeo.imagedemo.di

import android.content.Context
import com.fitpeo.imagedemo.BuildConfig
import com.fitpeo.imagedemo.network.ApiDataSource
import com.fitpeo.imagedemo.network.ApiRepository
import com.fitpeo.imagedemo.network.ApiServices
import com.fitpeo.imagedemo.utils.NullOnEmptyConverterFactory
import com.fitpeo.imagedemo.utils.SharedPreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.jvm.Throws

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val BASE_URL = "http://192.168.29.67:3000"
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val httpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        httpClientBuilder.cookieJar(JavaNetCookieJar(cookieManager))
        httpClientBuilder.addInterceptor(HeaderInterceptor())
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val client = httpClientBuilder
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    //adding data to header to make api request
    private fun HeaderInterceptor(): Interceptor {

        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                var request: Request = chain.request()
                request = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "*/*")
                    .method(request.method, request.body)
                    .build()
                return chain.proceed(request)
            }
        }
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)

    @Singleton
    @Provides
    fun provideApiDataSource(apiServices: ApiServices, sharedPreferenceManager: SharedPreferenceManager, @ApplicationContext context: Context) = ApiDataSource(apiServices,sharedPreferenceManager,context)

    @Singleton
    @Provides
    fun provideRepository(apiDataSource: ApiDataSource) =
        ApiRepository(apiDataSource)

}