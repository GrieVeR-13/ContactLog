package net.grieverc.contactlog.repo.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import net.grieverc.contactlog.repo.SpecialtyModel
import net.grieverc.contactlog.repo.WorkerModel
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
data class ResponseRemoteItem(
    val response: List<WorkerRemoteItem>
) {
    fun toModelList() =
        response
            .map { it.toModel() }
            .filterNotNull()
}

@JsonClass(generateAdapter = true)
data class WorkerRemoteItem(
    val f_name: String,
    val l_name: String,
    val birthday: LocalDate?,
    val specialty: List<SpecialtyRemoteItem>
) {
    fun toModel() =
        if (specialty.isNotEmpty()) {
            val specialtyModel =
                SpecialtyModel(specialty.first().specialty_id.toString(), specialty.first().name)
            WorkerModel(
                firstName = f_name,
                surname = l_name,
                age = 1,
                specialtyId = specialtyModel.id
            ).apply {
                specialty = specialtyModel
            }

        } else
            null
}

@JsonClass(generateAdapter = true)
data class SpecialtyRemoteItem(
    val specialty_id: Int,
    val name: String
)

class MoshiLocalDateAdapter {
    private val formatter = DateTimeFormatter.ofPattern(
        "[yyyy-MM-dd]" +
                "[dd-MM-yyyy]"
    )

    @FromJson
    fun fromJson(dateString: String): LocalDate? =
        if (dateString.isNotEmpty()) LocalDate.parse(dateString, formatter) else null
}
