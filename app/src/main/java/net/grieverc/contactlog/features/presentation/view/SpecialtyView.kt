package net.grieverc.contactlog.features.presentation.view

import net.grieverc.contactlog.features.domain.model.SpecialtyModel

data class SpecialtyView(
    val id: String,
    val name: String,
    val description: String
) {
}

fun SpecialtyModel.toView() =
    SpecialtyView(
        id,
        name,
        description
    )