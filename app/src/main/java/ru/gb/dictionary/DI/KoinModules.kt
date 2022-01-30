package geekbrains.ru.translator.di


import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.model.retrofit.RetrofitImplementation
import ru.gb.dictionary.presenter.MainInteract
import ru.gb.dictionary.viewmodel.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitImplementation()) }
}

val mainScreen = module {
    factory { MainInteract(get(named(NAME_REMOTE))) }
    factory { MainViewModel(get()) }
}
