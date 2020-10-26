package com.ost.rj.mge.testat.model.storage.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "https://ideasharingplatform.firebaseio.com/"

    private val gson = GsonBuilder()
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: IdeasRemoteDbApi by lazy { retrofit.create(IdeasRemoteDbApi::class.java)}


}

