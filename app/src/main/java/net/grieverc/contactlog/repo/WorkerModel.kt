package net.grieverc.contactlog.repo

import java.util.*

data class WorkerModel(
    var id: String = UUID.randomUUID().toString(),
    var firstName: String,
    val surname: String,
    val age: Int,
    val specialtyId: String,
    val specialtyModel: SpecialtyModel? = null
) {
}