package net.grieverc.contactlog.core

import java.util.*

data class SpecialtyModel(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    val description: String = ""
) {
}