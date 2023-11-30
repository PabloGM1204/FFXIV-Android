package com.example.ffxivproject.data.api.repository

import com.example.ffxivproject.data.api.db.FFXIVDBRepository
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
    private val apiRepository: MountApiRepository
) {
    val mount: Flow<List<Mount>>
        get() {
            val list = dbRespository.allMount.map {
                it.asMount()
            }
            return list
        }

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val apiMount = apiRepository.getAllMount()
            dbRespository.insert(apiMount.asEntityModel())
        }
    }
}