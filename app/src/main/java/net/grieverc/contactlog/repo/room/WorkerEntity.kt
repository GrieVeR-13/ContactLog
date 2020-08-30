package net.grieverc.contactlog.repo.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import net.grieverc.contactlog.repo.WorkerModel
import java.util.*

const val C_TableName_Worker = "Worker"

//@Entity(tableName = C_TableName_Worker, indices = [Index(value = ["id"])])
@Entity(
    tableName = C_TableName_Worker,
    foreignKeys = [ForeignKey(
        entity = SpecialtyEntity::class,
        parentColumns = ["specialtyId"],
        childColumns = ["specialtyFId"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["specialtyFId"])]
)
data class WorkerEntity(
    @PrimaryKey
    var workerId: String = UUID.randomUUID().toString(),
    var first_name: String,
    var surname: String,
    val age: Int,
    val specialtyFId: String
) {

    constructor(model: WorkerModel) : this(
        model.id,
        model.firstName,
        model.surname,
        model.age,
        model.specialtyId
    )

    fun toModel() = WorkerModel(
        workerId,
        first_name,
        surname,
        age,
        specialtyFId
    )
}

fun WorkerModel.toEntity() =
    WorkerEntity(this)

