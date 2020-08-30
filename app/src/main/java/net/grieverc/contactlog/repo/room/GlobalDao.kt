package net.grieverc.contactlog.repo.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import net.grieverc.contactlog.repo.room.union.SpecialtyWithWorkersUnion
import net.grieverc.contactlog.repo.room.union.WorkerWithSpecialtyUnion

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
//    @Query("SELECT * FROM $C_TableName_Worker WHERE workerId = :workerId")
//    fun loadWorkerById(workerId: String): Flow<WorkerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: WorkerEntity)


    //SpecialtyWithWorkerList
    @Transaction
    @Query("SELECT * FROM $C_TableName_Specialty")
    fun loadSpecialtyWithWorkerList(): Flow<List<SpecialtyWithWorkersUnion>>

    @Transaction
    @Query("SELECT * FROM $C_TableName_Specialty WHERE specialtyId = :specialtyId")
    fun loadWorkerListBySpecialtyId(specialtyId: String): Flow<SpecialtyWithWorkersUnion?>

    @Transaction
    @Query("SELECT * FROM $C_TableName_Worker, $C_TableName_Specialty WHERE $C_TableName_Worker.specialtyFId = $C_TableName_Specialty.specialtyId AND $C_TableName_Worker.workerId = :workerId")
    fun loadWorkerById(workerId: String): Flow<WorkerWithSpecialtyUnion?>

    @Transaction
    fun insert(specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>) {
        specialtyWithWorkersUnionList.forEach { specialtyWithWorkersUnion ->
            insert(specialtyWithWorkersUnion.specialty)
            for (worker in specialtyWithWorkersUnion.workerList) {
                insert(worker)
            }
        }
    }
}