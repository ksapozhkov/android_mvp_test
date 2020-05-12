package com.lifehacktestapp.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.napoleontest.domain.model.Offer

@Database(entities = [Offer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao
}