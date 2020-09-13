package net.grieverc.contactlog.features.presentation.view

import net.grieverc.contactlog.features.domain.model.WorkerModel
import org.threeten.bp.LocalDate
import java.util.*

data class WorkerView(
    val id: String,
    val firstName: String,
    val surname: String,
    val birthDate: LocalDate?,
    val age: Int?,
    val specialtyName: String?
) {

}

fun WorkerModel.toView() =
    WorkerView(
        id,
        firstName,
        surname,
        birthDate,
        age(),
        specialty?.name
    )