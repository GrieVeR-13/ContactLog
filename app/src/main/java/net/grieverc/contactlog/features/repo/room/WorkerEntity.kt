package net.grieverc.contactlog.features.repo.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import net.grieverc.contactlog.features.domain.model.WorkerModel
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

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
    val specialtyFId: String
) {

    fun toModel() = WorkerModel(
        workerId,
        first_name,
        surname,
        birthDate,
        specialtyFId
    )
}

fun WorkerModel.toEntity() =
    WorkerEntity(
        id,
        firstName,
        surname,
        birthDate,
        specialtyId
    )

object DateTypeConverter {
    private val formatter = DateTimeFormatter.ofPattern("[dd.MM.yyyy]")

    @TypeConverter
    @JvmStatic
    fun toLocalDate(dateString: String?) =
        dateString?.let {
            LocalDate.parse(it, formatter)
        }


    @TypeConverter
    @JvmStatic
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }

}