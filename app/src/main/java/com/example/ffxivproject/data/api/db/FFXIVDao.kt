package com.example.ffxivproject.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FFXIVDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMount(listMountEntity: List<MountEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArmour(listArmourEntity: List<ArmourEntity>)

    @Query("SELECT * FROM mount")
    fun getAll(): Flow<List<MountEntity>>

    @Query("SELECT * FROM mount WHERE id = :mountId")
    suspend fun getMountById(mountId: String): MountEntity

    @Query("SELECT * FROM armour")
    fun getAllArmour(): Flow<List<ArmourEntity>>

    @Query("SELECT * FROM armour WHERE id = :armourId")
    suspend fun getArmourById(armourId: String): ArmourEntity
}