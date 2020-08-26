package net.grieverc.contactlog.repo

import androidx.room.*
import java.util.*

const val C_TableName_Specialty = "Specialty"

@Entity(tableName = C_TableName_Specialty, indices = [Index(value = ["id"])])
data class SpecialtyEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var desciption: String
) {

    constructor(specialtyModel: SpecialtyModel) : this(specialtyModel.id, specialtyModel.name, specialtyModel.description)
    fun toModel() = SpecialtyModel(id, name, desciption)


    @Dao
    interface Store {
        @Query("SELECT * FROM $C_TableName_Specialty")
        fun loadAll(): List<SpecialtyEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(specialtyEntity: SpecialtyEntity)
    }
}