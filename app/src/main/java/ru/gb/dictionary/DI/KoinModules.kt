package ru.gb.dictionary.DI



import androidx.room.Room
import org.koin.dsl.module


import ru.gb.dictionary.presenter.MainInteract

import ru.gb.dictionary.viewmodel.MainViewModel
import ru.gb.model.DataModel
import ru.gb.repository.repository.Repository
import ru.gb.repository.repository.RepositoryImpl
import ru.gb.repository.repository.RepositoryImplLocal
import ru.gb.repository.repository.RepositoryLocal
import ru.gb.repository.retrofit.RetrofitImplementation
import ru.gb.repository.room.HistoryDataBase
import ru.gb.repository.room.RoomDataBaseImplementation
import values.HistoryInteractor

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteract(get(), get()) }
}

val historyScreen = module {
    factory { ru.gb.historyscreen.history.HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
