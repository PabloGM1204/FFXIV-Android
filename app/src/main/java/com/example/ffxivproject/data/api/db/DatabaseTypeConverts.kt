package com.example.ffxivproject.data.api.db

import androidx.room.TypeConverter
import com.example.ffxivproject.data.api.armour.Recursos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecursosListTypeConverter {
    @TypeConverter
    fun fromRecursosList(recursosList: List<Recursos>): String{
        return Gson().toJson(recursosList)
    }

    @TypeConverter
    fun toRecursosList(recursosListString: String): List<Recursos>{
        val type = object : TypeToken<List<Recursos>>() {}.type
        return Gson().fromJson(recursosListString, type)
    }
}