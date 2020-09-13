package net.grieverc.contactlog.features.repo.room

import androidx.room.*
import net.grieverc.contactlog.features.domain.model.SpecialtyModel
import net.grieverc.contactlog.features.domain.model.WorkerModel
import org.threeten.bp.LocalDate

const val C_TableName_Worker = "Worker"

@Entity(
    tableName = C_TableName_Worker
)
@TypeConverters(DateTypeConverter::class)
data class WorkerEntity(
    @PrimaryKey
    var workerId: String,
    var first_name: String,
    var surname: String,
    val birthDate: LocalDate?
) {
    fun toModel(specialtyList: List<SpecialtyModel>) = WorkerModel(
        workerId,
        first_name,
        surname,
        birthDate,
        specialtyList
    )
}

fun WorkerModel.toEntity() =
    WorkerEntity(
        id,
        firstName,
        surname,
        birthDate
    )


