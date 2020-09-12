package net.grieverc.contactlog.features.domain.model

import java.util.*

data class SpecialtyModel(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    val description: String = ""
) {
}