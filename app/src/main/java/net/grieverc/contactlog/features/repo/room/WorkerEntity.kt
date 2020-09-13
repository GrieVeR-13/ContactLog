package net.grieverc.contactlog.features.repo.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import net.grieverc.contactlog.core.service.FormatterService
import net.grieverc.contactlog.features.domain.model.SpecialtyModel
import net.grieverc.contactlog.features.domain.model.WorkerModel
import org.threeten.bp.LocalDate

const val C_TableName_Worker = "Worker"

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

@TypeConverters(DateTypeConverter::class)
data class WorkerEntity(
    @PrimaryKey
    var workerId: String,
    var first_name: String,
    var surname: String,
    val birthDate: LocalDate?,
    val specialtyFId: String?
) {

    fun toModel(specialty: SpecialtyModel) = WorkerModel(
        workerId,
        first_name,
        surname,
        birthDate,
        specialty
    )
}

fun WorkerModel.toEntity() =
    WorkerEntity(
        id,
        firstName,
        surname,
        birthDate,
        specialty?.id
    )

