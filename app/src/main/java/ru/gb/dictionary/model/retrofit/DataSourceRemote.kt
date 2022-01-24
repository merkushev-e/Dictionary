package ru.gb.dictionary.model.retrofit


import io.reactivex.rxjava3.core.Observable
import ru.gb.dictionary.model.DataSource
import ru.gb.dictionary.model.data.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }

}