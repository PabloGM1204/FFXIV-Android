package com.example.ffxivproject.data.api.character

import com.example.ffxivproject.data.api.db.FFXIVDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(private val dao: FFXIVDao){
    suspend fun getAllCharacters(): List<CharacterModel>{
        val charactersFlow = dao.getAllCharacter()
        val characterList = mutableListOf<CharacterModel>()
        charactersFlow.collect { characterEntityList ->
            // Mapear cada CharacterEntity a CharacterModel y agregarlo a la lista
            characterList.addAll(characterEntityList.map {
                it.toCharacterModel() })
        }
        return characterList
    }
}