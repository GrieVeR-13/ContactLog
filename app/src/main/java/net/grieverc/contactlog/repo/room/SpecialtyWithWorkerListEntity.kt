package net.grieverc.contactlog.repo.room

import androidx.room.Embedded
import androidx.room.Relation
import net.grieverc.contactlog.repo.SpecialtyModel
import net.grieverc.contactlog.repo.SpecialtyWithWorkerListModel

data class SpecialtyWithWorkerListEntity(
    @Embedded val specialty: SpecialtyEntity,
    @Relation(
        parentColumn = "id",
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
