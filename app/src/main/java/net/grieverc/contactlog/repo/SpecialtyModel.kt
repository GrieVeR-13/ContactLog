package net.grieverc.contactlog.repo

import java.util.*

data class SpecialtyModel(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    val description: String = ""
) {
}