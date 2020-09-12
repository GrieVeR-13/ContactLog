package net.grieverc.contactlog.features.repo.remote

import com.squareup.moshi.JsonClass
import net.grieverc.contactlog.features.domain.model.SpecialtyModel
import net.grieverc.contactlog.features.domain.model.WorkerModel
import org.threeten.bp.LocalDate
import java.util.*

@JsonClass(generateAdapter = true)
data class ResponseRemoteEntity(
    val response: List<WorkerRemoteEntity>
) {
    fun toModelList() =
        response.mapNotNull { it.toModel() }
}

@JsonClass(generateAdapter = true)
data class WorkerRemoteEntity(
    val f_name: String,
    val l_name: String,
    val birthday: LocalDate?,
    val specialty: List<SpecialtyRemoteEntity>
) {
    fun toModel() =
        if (specialty.isNotEmpty()) {
            val specialtyModel =
                SpecialtyModel(
                    specialty.first().specialty_id.toString(),
                    specialty.first().name
                )
            WorkerModel(
                UUID.randomUUID().toString(),
                firstName = f_name,
                surname = l_name,
                birthDate = birthday,
                specialty = specialtyModel
            )
        } else
            null
}

@JsonClass(generateAdapter = true)
data class SpecialtyRemoteEntity(
    val specialty_id: Int,
    val name: String
)
