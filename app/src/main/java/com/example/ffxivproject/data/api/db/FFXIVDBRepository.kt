package com.example.ffxivproject.data.api.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FFXIVDBRepository @Inject constructor(private val mountDao: FFXIVDao) {
    val allMount: Flow<List<MountEntity>> = mountDao.getAll()

    @WorkerThread
    suspend fun insert(listMountEntity: List<MountEntity>){
        mountDao.insert(listMountEntity)
    }

    suspend fun getMounById(mountId: String): MountEntity {
        return mountDao.getMountById(mountId)
    }
}