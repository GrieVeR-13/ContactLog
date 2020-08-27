package net.grieverc.contactlog.repo.worker

import java.util.*

data class WorkerModel(
    var id: String = UUID.randomUUID().toString(),
    var firstName: String,
    val surname: String,
    val age: Int
) {
}