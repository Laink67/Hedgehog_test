package com.laink.hedgehog_test.api

import com.laink.hedgehog_test.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        // lazy означает, что мы инициализируем это здесь только один раз
        private val retrofit by lazy {
            // Прикрепление к нашему retrofit object, чтобы видеть какие запросы мы делаем и ответы на них
//            val logging = HttpLoggingInterceptor()
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

            // Используем этот перехватчик для создания клиента
            val client = OkHttpClient.Builder()
//                .addInterceptor(logging)
                .build()
            // Теперь можем использовать клиента и передать его в retrofit instance
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Чтобы определить, как интерпритировать ответ
                .client(client)
                .build()
        }

        // Получение нашего экземпляра API из Retrofit.Builder для запросов в дальнейшем
        val api by lazy {
            retrofit.create(JokesApi::class.java)
        }
    }
}