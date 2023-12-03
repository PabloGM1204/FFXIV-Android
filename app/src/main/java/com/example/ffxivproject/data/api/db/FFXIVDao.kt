package com.example.ffxivproject.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FFXIVDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listMountEntity: List<MountEntity>)

    @Query("SELECT * FROM mount")
    fun getAll(): Flow<List<MountEntity>>

    @Query("SELECT * FROM mount WHERE id = :mountId")
    suspend fun getMountById(mountId: String): MountEntity
}