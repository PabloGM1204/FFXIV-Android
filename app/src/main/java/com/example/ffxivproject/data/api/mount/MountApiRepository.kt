package com.example.ffxivproject.data.api.mount

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MountApiRepository @Inject constructor(private val service: MountService) {
    suspend fun getAllMount(): List<MountApiModel> {
        val simpleList = service.apiArmour.getAllMounts(300, 0)
        val mountApiModel = simpleList.results.map {
            mountListItem ->  service.apiArmour.getDetailMounts(mountListItem.id).asApiModel()
        }
        return mountApiModel
    }
}