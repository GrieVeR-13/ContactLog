package net.grieverc.contactlog.features.domain.model

import org.threeten.bp.LocalDate
import java.util.*

data class WorkerModel(
    var id: String,
    var firstName: String,
    val surname: String,
    val birthDate: LocalDate?,
    var specialty: SpecialtyModel?
) {

    fun age() =
        birthDate?.let { birthDate.until(LocalDate.now()).years}
}
