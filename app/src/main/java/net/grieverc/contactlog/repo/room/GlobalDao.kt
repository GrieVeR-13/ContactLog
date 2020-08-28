package net.grieverc.contactlog.repo.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalDao {
    //Specialty
    @Query("SELECT * FROM $C_TableName_Specialty")
    fun loadSpecialty(): Flow<List<SpecialtyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: SpecialtyEntity)

    @Delete
    fun delete(vararg entity: SpecialtyEntity)


    //Worker
    @Query("SELECT * FROM $C_TableName_Worker WHERE id = :workerId")
    fun loadWorkerById(workerId: String): Flow<WorkerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: WorkerEntity)


    //SpecialtyWithWorkerList
    @Transaction
    @Query("SELECT * FROM $C_TableName_Specialty")
    fun loadSpecialtyWithWorkerList(): Flow<List<SpecialtyWithWorkerListEntity>>

    @Transaction
    @Query("SELECT * FROM $C_TableName_Specialty WHERE id = :specialtyId")
    fun loadSpecialtyWithWorkerListById(specialtyId: String): Flow<SpecialtyWithWorkerListEntity>

    @Transaction
    fun insert(specialtyWithWorkerList: SpecialtyWithWorkerListEntity) {
        insert(specialtyWithWorkerList.specialty)
        for (worker in specialtyWithWorkerList.workerList) {
            insert(worker)
        }
    }
}