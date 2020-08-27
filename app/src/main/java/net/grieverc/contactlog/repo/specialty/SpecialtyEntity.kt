package net.grieverc.contactlog.repo.specialty

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

const val C_TableName_Specialty = "Specialty"

@Entity(tableName = C_TableName_Specialty, indices = [Index(value = ["id"])])
data class SpecialtyEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
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
            id,
            name,
            desciption
        )

    @Dao
    interface Store {
        @Query("SELECT * FROM $C_TableName_Specialty")
        fun loadAll(): Flow<List<SpecialtyEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg entity: SpecialtyEntity)

        @Delete
        fun delete(vararg entity: SpecialtyEntity)
    }
}

fun SpecialtyModel.toEntity() =
    SpecialtyEntity(this)