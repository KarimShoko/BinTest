package com.example.bintest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardInfoDao {
    @Query("SELECT * FROM card_info_list ")
    fun getCardInfoList(): LiveData<List<CardInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardInfo(cardInfo: CardInfoDbModel)

    @Query("DELETE FROM card_info_list WHERE id = (SELECT id FROM card_info_list ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldest()
}
