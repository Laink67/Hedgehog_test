package com.laink.hedgehog_test.api

import com.laink.hedgehog_test.models.Request
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesApi {

    @GET("jokes/random/{number}")
    suspend fun getRandomJokes(
        @Path("number")
        number: String
    ): Response<Request>
}