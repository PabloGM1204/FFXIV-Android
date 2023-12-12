package com.example.ffxivproject.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FFXIVDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMount(listMountEntity: List<MountEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArmour(listArmourEntity: List<ArmourEntity>)

    @Update
    suspend fun updateArmour(armourActualizar: ArmourEntity)

    @Query("SELECT * FROM character")
    fun getAllCharacter(): Flow<List<CharacterEntity>>

    @Transaction // Indica que vamos a trabajar con varias tablas y lo hace de manera atomica (Se hace para que funcionen todas o ningunas y asi evitar errores)
    @Query("SELECT * FROM character")
    fun getCharacterWithArmours(): List<CharacterWithArmour>

    @Query("SELECT * FROM mount")
    fun getAll(): Flow<List<MountEntity>>

    @Query("SELECT * FROM mount WHERE id = :mountId")
    suspend fun getMountById(mountId: String): MountEntity

    @Query("SELECT * FROM armour")
    fun getAllArmour(): Flow<List<ArmourEntity>>

    @Query("SELECT * FROM armour WHERE armourId = :armourId")
    suspend fun getArmourById(armourId: String): ArmourEntity
}