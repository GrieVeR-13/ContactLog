package net.grieverc.contactlog.repo.room.union

import androidx.room.Embedded
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity

data class WorkerWithSpecialtyUnion(
    @Embedded
    val worker: WorkerEntity,
    @Embedded
    val specialty: SpecialtyEntity
) {
    fun toModel() = worker.toModel().also { it.specialty = specialty.toModel() }

}
