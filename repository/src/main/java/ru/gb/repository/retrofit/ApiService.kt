package ru.gb.repository.retrofit


import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.model.DataModel

interface ApiService {
    @GET("words/search")
    fun searchAsync(
        @Query("search") wordToSearch: String
    ): Deferred<List<DataModel>>

}