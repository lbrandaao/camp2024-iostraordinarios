package com.example.journey.data.remote

import com.example.journey.data.remote.services.JourneyService
import com.example.journey.data.remote.services.SuperpowerService
import com.example.journey.data.remote.services.TagService
import com.example.journey.data.remote.services.UserService
import com.example.journey.data.remote.services.PostService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .build()

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://ioasys-journey.onrender.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    val journeyService: JourneyService by lazy {
        retrofit.create(JourneyService::class.java)
    }

    val postService: PostService by lazy {
        retrofit.create(PostService::class.java)
    }

    val superpowerService: SuperpowerService by lazy {
        retrofit.create(SuperpowerService::class.java)
    }

    val tagService: TagService by lazy {
        retrofit.create(TagService::class.java)
    }
}