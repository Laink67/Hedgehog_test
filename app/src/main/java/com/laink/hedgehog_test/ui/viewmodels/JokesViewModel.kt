package com.laink.hedgehog_test.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laink.hedgehog_test.models.Request
import com.laink.hedgehog_test.repositories.JokeRepository
import com.laink.hedgehog_test.util.Response
import kotlinx.coroutines.launch

class JokesViewModel @ViewModelInject constructor(
    val jokesRepository: JokeRepository
) : ViewModel() {

    private val _resultJokes: MutableLiveData<Response<Request>> = MutableLiveData()

    val resultJokes: LiveData<Response<Request>> = _resultJokes

    fun getSomeJokes(number: String) = viewModelScope.launch {
        _resultJokes.postValue(Response.Loading())
        _resultJokes.postValue(
            checkResponse(
                jokesRepository.getSomeJokes(number)
            )
        )
    }

    private fun checkResponse(response: retrofit2.Response<Request>): Response<Request> {
        if (response.isSuccessful) {
            response.body()?.let { Request ->
                return Response.Success(Request)
            }
        }

        return Response.Error(response.message())
    }

}