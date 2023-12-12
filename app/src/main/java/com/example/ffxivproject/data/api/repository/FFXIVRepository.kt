package com.example.ffxivproject.data.api.repository

import com.example.ffxivproject.data.api.armour.ArmourApiRepository
import com.example.ffxivproject.data.api.armour.asEntityModel
import com.example.ffxivproject.data.api.character.CharacterRepository
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.FFXIVDBRepository
import com.example.ffxivproject.data.api.db.MountEntity
import com.example.ffxivproject.data.api.db.asArmour
import com.example.ffxivproject.data.api.db.asCharacter
import com.example.ffxivproject.data.api.db.asMount
import com.example.ffxivproject.data.api.mount.MountApiRepository
import com.example.ffxivproject.data.api.mount.asEntityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FFXIVRepository @Inject constructor(
    private val dbRespository: FFXIVDBRepository,
    private val apiMountRepository: MountApiRepository,
    private val apiArmourRepository: ArmourApiRepository,
    private val characterRepository: CharacterRepository
) {
    val mount: Flow<List<Mount>>
        get() {
            val list = dbRespository.allMount.map {
                it.asMount()
            }
            return list
        }

    val armour: Flow<List<Armour>>
        get(){
            val list = dbRespository.allArmour.map {
                it.asArmour()
            }
            return  list
        }

    val character: Flow<List<Character>>
        get(){
            val list = dbRespository.allCharacter.map {
                it.asCharacter()
            }
            return list
        }

    suspend fun getMountId(mountId: String): MountEntity{
        return dbRespository.getMounById(mountId)
    }

    suspend fun getArmourId(armourId: String): ArmourEntity {
        return dbRespository.getArmourById(armourId)
    }

    suspend fun updateArmourSelected(armourId: String) {
        withContext(Dispatchers.IO) {
            // Llamo al método para actulizarlo
            dbRespository.updateAmour(armourId)
        }
    }

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val apiMount = apiMountRepository.getAllMount()
            dbRespository.insertMount(apiMount.asEntityModel())
            val apiArmour = apiArmourRepository.getAllArmour()
            dbRespository.insertArmour(apiArmour.asEntityModel())
        }
    }
}