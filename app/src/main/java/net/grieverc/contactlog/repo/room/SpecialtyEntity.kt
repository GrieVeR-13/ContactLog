package net.grieverc.contactlog.repo.room

import androidx.room.*
import net.grieverc.contactlog.repo.SpecialtyModel
import java.util.*

const val C_TableName_Specialty = "Specialty"

@Entity(tableName = C_TableName_Specialty, indices = [Index(value = ["specialtyId"])])
data class SpecialtyEntity(
    @PrimaryKey
    var specialtyId: String = UUID.randomUUID().toString(),
    var name: String,
    var desciption: String
) {

    constructor(specialtyModel: SpecialtyModel) : this(
        specialtyModel.id,
        specialtyModel.name,
        specialtyModel.description
    )

    fun toModel() =
        SpecialtyModel(
            specialtyId,
            name,
            desciption
        )
}

fun SpecialtyModel.toEntity() =
    SpecialtyEntity(this)
