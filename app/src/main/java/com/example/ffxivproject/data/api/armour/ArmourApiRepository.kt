package com.example.ffxivproject.data.api.armour

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArmourApiRepository @Inject constructor(private val service: ArmourService) {
    suspend fun getAllArmour(): List<ArmourApiModel>{
        val simpleList = service.apiArmour.getAllArmour(10,0)
        val armourApiModel = simpleList.results.map{
            armourListItem -> service.apiArmour.getDetailArmour(armourListItem.id).asApiModel()
        }
        return armourApiModel
    }
}