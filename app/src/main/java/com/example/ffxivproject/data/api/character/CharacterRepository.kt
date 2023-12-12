package com.example.ffxivproject.data.api.character

import com.example.ffxivproject.data.api.db.CharacterWithArmour
import com.example.ffxivproject.data.api.db.FFXIVDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(private val dao: FFXIVDao){
    // En el caso de que quiera hacer algo mas
    suspend fun getCharactersWithArmour(): List<CharacterWithArmour> {
        return dao.getCharacterWithArmours()
    }
}