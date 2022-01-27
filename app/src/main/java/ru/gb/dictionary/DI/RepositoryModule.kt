package geekbrains.ru.translator.di

import dagger.Module
import dagger.Provides

import ru.gb.dictionary.DI.NAME_REMOTE
import ru.gb.dictionary.model.DataSource
import ru.gb.dictionary.model.data.DataModel
import ru.gb.dictionary.model.repository.Repository
import ru.gb.dictionary.model.repository.RepositoryImpl
import ru.gb.dictionary.model.retrofit.RetrofitImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
     fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceRemote)


    @Provides
    @Singleton
    @Named(NAME_REMOTE)
     fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()


}
