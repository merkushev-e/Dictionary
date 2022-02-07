package ru.gb.dictionary.DI


import androidx.room.Room
import geekbrains.ru.translator.di.NAME_LOCAL
import geekbrains.ru.translator.di.NAME_REMOTE
import ru.gb.dictionary.model.room.HistoryDataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.model.repository.RepositoryImplLocal
import ru.gb.dictionary.model.repository.RepositoryLocal
import ru.gb.dictionary.model.retrofit.RetrofitImplementation
import ru.gb.dictionary.model.room.RoomDataBaseImplementation
import ru.gb.dictionary.presenter.HistoryInteractor
import ru.gb.dictionary.presenter.MainInteract
import ru.gb.dictionary.viewmodel.HistoryViewModel
import ru.gb.dictionary.viewmodel.MainViewModel

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
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
