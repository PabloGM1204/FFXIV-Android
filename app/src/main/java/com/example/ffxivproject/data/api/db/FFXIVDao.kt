package com.example.ffxivproject.data.api.db

import androidx.room.Dao
import androidx.room.Delete
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(characterEntity: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCharacter(newCharacter: CharacterEntity)

    @Update
    suspend fun updateArmour(armourActualizar: ArmourEntity)

    @Update
    suspend fun updateMount(mountActualizar: MountEntity)

    @Query("SELECT * FROM character")
    fun getAllCharacter(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacterById(characterId: String): CharacterEntity

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)

    @Query("SELECT * FROM mount")
    fun getAll(): Flow<List<MountEntity>>

    @Query("SELECT * FROM mount WHERE id = :mountId")
    suspend fun getMountById(mountId: String): MountEntity

    @Query("SELECT * FROM armour")
    fun getAllArmour(): Flow<List<ArmourEntity>>

    @Query("SELECT * FROM armour WHERE armourId = :armourId")
    suspend fun getArmourById(armourId: String): ArmourEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterArmour(listCharacterArmour: List<CharacterArmour>)

    // Obtener todas las armaduras asociadas a un personaje
    @Query("SELECT * FROM armour WHERE armourId IN (SELECT armourId FROM character_armour WHERE characterId = :characterId)")
    suspend fun getArmoursForCharacter(characterId: Int): List<ArmourEntity>

    @Query("SELECT * FROM character WHERE id IN (SELECT characterId FROM character_armour WHERE armourId = :armourId)")
    suspend fun getCharactersForArmour(armourId: Int): List<CharacterEntity>
}