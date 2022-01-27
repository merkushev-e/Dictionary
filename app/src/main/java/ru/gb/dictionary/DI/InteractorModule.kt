package ru.gb.dictionary.DI

import dagger.Module
import dagger.Provides
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.presenter.MainInteract
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    fun provieInteractor(
        @Named(NAME_REMOTE) repository: Repository<List<DataModel>>
    ) =  MainInteract(repository)
}