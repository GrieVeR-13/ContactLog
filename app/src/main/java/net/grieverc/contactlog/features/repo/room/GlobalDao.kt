package net.grieverc.contactlog.features.repo.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import net.grieverc.contactlog.features.repo.room.union.SpecialtyWithWorkersUnion
import net.grieverc.contactlog.features.repo.room.union.WorkerWithSpecialtysUnion

@Dao
interface GlobalDao {
    //Worker
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: WorkerEntity)

    //Specialty
    @Query("SELECT * FROM $C_TableName_Specialty")
    fun loadSpecialty(): Flow<List<SpecialtyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: SpecialtyEntity)

    @Delete
    fun delete(vararg entity: SpecialtyEntity)


    //WorkerSpecialtyCrossRef
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: WorkerSpecialtyCrossRef)


    //SpecialtyWithWorkersUnion
    @Transaction
    @Query("SELECT * FROM $C_TableName_Specialty WHERE specialtyId = :specialtyId")
    fun loadWorkerListBySpecialtyId(specialtyId: String): Flow<SpecialtyWithWorkersUnion?>


    //WorkerWithSpecialtysUnion
    @Transaction
    @Query("SELECT * FROM $C_TableName_Worker WHERE workerId = :workerId")
    fun loadWorkerById(workerId: String): Flow<WorkerWithSpecialtysUnion?>

    @Transaction
    fun insert(workerWithSpecialtysUnionList: List<WorkerWithSpecialtysUnion>) {
        workerWithSpecialtysUnionList.forEach { workerWithSpecialtysUnion ->
            insert(workerWithSpecialtysUnion.worker)
            workerWithSpecialtysUnion.specialtyList.forEach {
                insert(it)
                insert(WorkerSpecialtyCrossRef(workerWithSpecialtysUnion.worker.workerId, it.specialtyId))
            }
        }
    }

}