package com.ost.rj.mge.testat.model.storage.network

import com.ost.rj.mge.testat.model.Idea
import retrofit2.Response
import retrofit2.http.*




const val API_AUTHKEY = "l7SSsrdQmeCUjWyynlITnO1ZZVVRm5orFJ4l8g9Q"

interface IdeasRemoteDbApi {
    @Headers("Content-Type: application/json",
        "Authorization: key=$API_AUTHKEY")
    @GET("ideas.json")
    //suspend fun getItems() : List<Idea>
    suspend fun getItems() : Response<HashMap<String, Idea>>


    @Headers("Content-Type: application/json",
        "Authorization: key=$API_AUTHKEY")
    @POST("ideas.json")
    suspend fun createIdea(@Body idea: Idea)

    //fun createIdea(@Header ("auth") auth : String, @Body idea: Idea)

    @PUT("ideas")
    fun updateIdea(@Body Idea: Idea)
}