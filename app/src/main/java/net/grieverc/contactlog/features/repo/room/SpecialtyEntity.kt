package net.grieverc.contactlog.features.repo.room

import androidx.room.*
import net.grieverc.contactlog.features.domain.model.SpecialtyModel
import java.util.*

const val C_TableName_Specialty = "Specialty"

@Entity(tableName = C_TableName_Specialty, indices = [Index(value = ["specialtyId"])])
data class SpecialtyEntity(
    @PrimaryKey
    var specialtyId: String,
    var name: String,
    var description: String
) {
    fun toModel() =
        SpecialtyModel(
            specialtyId,
            name,
            description
        )
}

fun SpecialtyModel.toEntity() =
    SpecialtyEntity(
        id,
        name,
        description
    )
