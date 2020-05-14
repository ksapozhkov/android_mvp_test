package com.lifehacktestapp.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.napoleontest.domain.model.Offer

@Dao
interface OfferDao {
    @Query("SELECT * FROM Offer")
    suspend fun getAll(): List<Offer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(offers: List<Offer>)

}