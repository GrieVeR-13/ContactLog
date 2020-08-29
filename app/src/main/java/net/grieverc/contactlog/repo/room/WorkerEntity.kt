package net.grieverc.contactlog.repo.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import net.grieverc.contactlog.repo.WorkerModel
import java.util.*

const val C_TableName_Worker = "Worker"

@Entity(tableName = C_TableName_Worker, indices = [Index(value = ["id"])])
//@Entity(
//    tableName = C_TableName_Worker,
//    foreignKeys = [ForeignKey(
//        entity = WorkerEntity::class,
//        parentColumns = ["specialty_id"], //from ColumnInfo of User class
//        childColumns = ["specialtyId"],
//        onDelete = CASCADE
//    )],
//    indices = [Index(value = ["specialtyId"])]
//)
data class WorkerEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var first_name: String,
    var surname: String,
    val age: Int,
    val specialtyId: String
) {

    constructor(model: WorkerModel) : this(
        model.id,
        model.firstName,
        model.surname,
        model.age,
        model.specialtyId
    )

    fun toModel() = WorkerModel(
        id,
        first_name,
        surname,
        age,
        specialtyId
    )
}

fun WorkerModel.toEntity() =
    WorkerEntity(this)

