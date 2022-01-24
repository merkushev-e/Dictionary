package ru.gb.dictionary.model.room

import io.reactivex.rxjava3.core.Observable
import ru.gb.dictionary.model.DataSource
import ru.gb.dictionary.model.data.DataModel

class DataSourceLocal : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}