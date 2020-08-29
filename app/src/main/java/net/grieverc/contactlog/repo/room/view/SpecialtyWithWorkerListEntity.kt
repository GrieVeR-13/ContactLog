package net.grieverc.contactlog.repo.room.view

import androidx.room.Embedded
import androidx.room.Relation
import net.grieverc.contactlog.repo.SpecialtyWithWorkerListModel
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity

data class SpecialtyWithWorkerListEntity(
    @Embedded val specialty: SpecialtyEntity,
    @Relation(
        parentColumn = "specialty_id",
        entityColumn = "specialtyId"
    )
    val workerList: List<WorkerEntity>
) {
    fun toModel() =
        SpecialtyWithWorkerListModel(
            specialty.toModel(),
            workerList.map { it.toModel() }
        )
}
