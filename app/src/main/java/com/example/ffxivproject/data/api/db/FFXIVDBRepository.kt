package com.example.ffxivproject.data.api.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FFXIVDBRepository @Inject constructor(private val FFXIVDao: FFXIVDao) {
    val allMount: Flow<List<MountEntity>> = FFXIVDao.getAll()
    val allArmour: Flow<List<ArmourEntity>> = FFXIVDao.getAllArmour()
    val allCharacter: Flow<List<CharacterEntity>> = FFXIVDao.getAllCharacter()

    @WorkerThread
    suspend fun insertMount(listMountEntity: List<MountEntity>){
        FFXIVDao.insertMount(listMountEntity)
    }

    @WorkerThread
    suspend fun insertArmour(listArmourEntity: List<ArmourEntity>){
        FFXIVDao.insertArmour(listArmourEntity)
    }

    suspend fun insertCharacter(listCharacterEntity: List<CharacterEntity>){
        FFXIVDao.insertCharacter(listCharacterEntity)
    }

    @WorkerThread
    suspend fun updateAmour(armourId: String) {
        // Recojo la armadura de la api
        val actArmadura = FFXIVDao.getArmourById(armourId)
        // Le cambio el valor de selected
        actArmadura.selected = !actArmadura.selected
        // Actulizo en la BD
        FFXIVDao.updateArmour(actArmadura)
    }

    suspend fun getMounById(mountId: String): MountEntity {
        return FFXIVDao.getMountById(mountId)
    }

    suspend fun getArmourById(armourId: String): ArmourEntity {
        return FFXIVDao.getArmourById(armourId)
    }
}