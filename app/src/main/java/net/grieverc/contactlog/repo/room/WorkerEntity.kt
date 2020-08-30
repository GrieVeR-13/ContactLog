package net.grieverc.contactlog.repo.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import net.grieverc.contactlog.repo.WorkerModel
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
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

@TypeConverters(DateTypeConverter::class)
data class WorkerEntity(
    @PrimaryKey
    var workerId: String = UUID.randomUUID().toString(),
    var first_name: String,
    var surname: String,
    val birtyDate: LocalDate?,
    val specialtyFId: String
) {

    constructor(model: WorkerModel) : this(
        model.id,
        model.firstName,
        model.surname,
        model.birthDate,
        model.specialtyId
    )

    fun toModel() = WorkerModel(
        workerId,
        first_name,
        surname,
        birtyDate,
        specialtyFId
    )
}

fun WorkerModel.toEntity() =
    WorkerEntity(this)

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