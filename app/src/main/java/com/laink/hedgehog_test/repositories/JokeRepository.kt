package com.laink.hedgehog_test.repositories

import com.laink.hedgehog_test.api.JokesApi
import com.laink.hedgehog_test.api.RetrofitInstance
import javax.inject.Inject

class JokeRepository @Inject constructor(
    val jokesApi: JokesApi
) {

    suspend fun getSomeJokes(number: String) =
        jokesApi.getRandomJokes(number)
}