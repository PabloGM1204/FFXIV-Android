package com.example.ffxivproject.data.api.repository

import android.util.Log
import com.example.ffxivproject.data.api.armour.ArmourApiRepository
import com.example.ffxivproject.data.api.armour.asEntityModel
import com.example.ffxivproject.data.api.character.CharacterModel
import com.example.ffxivproject.data.api.character.CharacterRepository
import com.example.ffxivproject.data.api.character.asEntityModel
import com.example.ffxivproject.data.api.db.ArmourEntity
import com.example.ffxivproject.data.api.db.CharacterArmour
import com.example.ffxivproject.data.api.db.CharacterEntity
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

    val character: Flow<List<CharacterInv>>
        get(){
            val list = dbRespository.allCharacter.map {
                Log.d("CHARACTER", it.toString())
                it.asCharacter()
            }
            return  list
        }

    suspend fun getMountId(mountId: String): MountEntity{
        return dbRespository.getMounById(mountId)
    }

    suspend fun getArmourId(armourId: String): ArmourEntity {
        return dbRespository.getArmourById(armourId)
    }

    suspend fun getCharacterId(characterId: String): CharacterEntity {
        return dbRespository.getCharacterById(characterId)
    }

    suspend fun getCharacterWithArmours(characterId: String): List<Armour> {
        val armourEntities = dbRespository.getCharacterWithArmours(characterId)
        return armourEntities.asArmour()
    }

    suspend fun updateArmourSelected(armourId: String) {
        withContext(Dispatchers.IO) {
            // Llamo al método para actulizarlo
            dbRespository.updateAmour(armourId)
        }
    }

    suspend fun updateMount(mountId: String){
        withContext(Dispatchers.IO) {
            // Llamo al método para actulizarlo
            dbRespository.updateMount(mountId)
        }
    }

    suspend fun insertCharacterArmour(armourId: String, listCharacter: List<CharacterInv>){
        withContext(Dispatchers.IO) {
            val characterArmour = listCharacter.map {
                CharacterArmour(
                    0,
                    it.id,
                    armourId.toInt()
                )
            }
            dbRespository.insertCharacterArmour(characterArmour)
        }
    }

    suspend fun createNewCharacter(newCharacter: CharacterModel){
        Log.d("Nuevo", newCharacter.name)
        withContext(Dispatchers.IO) {
            val character = CharacterEntity(
                newCharacter.id,
                newCharacter.name,
                newCharacter.selection,
                newCharacter.kind
            )
            Log.d("Character", character.toString())
            dbRespository.insertCharacter(character)
        }
    }

    suspend fun deleteCharacter(character: CharacterEntity) {
        withContext(Dispatchers.IO){
            dbRespository.deleteCharacter(character)
        }
    }

    suspend fun getCharactersForArmour(armourId: Int): List<CharacterEntity> {
        return dbRespository.getCharactersForArmour(armourId)
    }

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val apiMount = apiMountRepository.getAllMount()
            dbRespository.insertMount(apiMount.asEntityModel())
            val apiArmour = apiArmourRepository.getAllArmour()
            dbRespository.insertArmour(apiArmour.asEntityModel())
            /*val dbCharacter = characterRepository.getAllCharacters()
            dbRespository.insertCharacter(dbCharacter.asEntityModel())*/
        }
    }
}