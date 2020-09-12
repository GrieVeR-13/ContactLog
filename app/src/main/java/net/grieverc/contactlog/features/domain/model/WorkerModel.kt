package net.grieverc.contactlog.features.domain.model

import org.threeten.bp.LocalDate
import java.util.*

data class WorkerModel(
    var id: String = UUID.randomUUID().toString(),
    var firstName: String,
    val surname: String,
    val birthDate: LocalDate?,
    val specialtyId: String
) {
    var specialty: SpecialtyModel? = null

    fun age() =
        birthDate?.let { LocalDate.now().year - birthDate.year }
}