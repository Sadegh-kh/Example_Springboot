package com.example.example_springboot.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.example_springboot.model.Student

@Database(entities = [Student::class], version = 2, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao

    companion object {
        private var database: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myDatabase.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }
    }
}