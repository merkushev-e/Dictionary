package ru.gb.dictionary.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import geekbrains.ru.translator.room.HistoryDao

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
