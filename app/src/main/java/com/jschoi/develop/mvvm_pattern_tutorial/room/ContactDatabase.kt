package com.jschoi.develop.mvvm_pattern_tutorial.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase() : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        private var mInstance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase? {
            if (mInstance == null) {
                synchronized(ContactDatabase::class) {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "contact"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return mInstance
        }
    }
}