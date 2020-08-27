package net.grieverc.contactlog.repo.worker

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

const val C_TableName_Worker = "Worker"

@Entity(tableName = C_TableName_Worker, indices = [Index(value = ["id"])])
data class WorkerEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var first_name: String,
    var surname: String,
    val age: Int
) {

    constructor(model: WorkerModel) : this(model.id, model.firstName, model.surname, model.age)

    fun toModel() = WorkerModel(id, first_name, surname, age)

    @Dao
    interface Store {
        @Query("SELECT * FROM $C_TableName_Worker")
        fun loadAll(): Flow<List<WorkerEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg entity: WorkerEntity)

        @Delete
        fun delete(vararg entity: WorkerEntity)
    }
}

fun WorkerModel.toEntity() = WorkerEntity(this)