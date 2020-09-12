package net.grieverc.contactlog.features.repo.room.union

import androidx.room.Embedded
import net.grieverc.contactlog.features.repo.room.SpecialtyEntity
import net.grieverc.contactlog.features.repo.room.WorkerEntity

data class WorkerWithSpecialtyUnion(
    @Embedded
    val worker: WorkerEntity,
    @Embedded
    val specialty: SpecialtyEntity
) {
    fun toModel() = worker.toModel().also { it.specialty = specialty.toModel() }

}
