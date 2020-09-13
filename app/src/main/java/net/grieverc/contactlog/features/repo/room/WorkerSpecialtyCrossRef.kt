package net.grieverc.contactlog.features.repo.room

import androidx.room.Entity

@Entity(primaryKeys = ["workerId", "specialtyId"])
data class WorkerSpecialtyCrossRef(
    val workerId: String,
    val specialtyId: String
)