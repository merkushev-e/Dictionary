package ru.gb.repository.retrofit


import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.model.data.DataModel
import ru.gb.model.dto.SearchResultDto

interface ApiService {
    @GET("words/search")
    fun searchAsync(
        @Query("search") wordToSearch: String
    ): Deferred<List<SearchResultDto>>

}