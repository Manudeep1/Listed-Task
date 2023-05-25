package com.example.listed_task.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listed_task.dashboard_data.Token

@Database(entities = [Token::class], version = 2)
abstract class TokenDatabase: RoomDatabase() {
    abstract fun tokenDao(): TokenDao

    companion object{

        @Volatile
        private var INSTANCE : TokenDatabase? = null
        //creating instance of our roomDB
        fun getDataBase(context: Context) : TokenDatabase{
            var instance = INSTANCE

            if (instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TokenDatabase::class.java,
                    "tokenDB").fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return instance

        }
    }
}