package net.grieverc.contactlog.features.repo.room.union

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.repo.room.SpecialtyEntity
import net.grieverc.contactlog.features.repo.room.WorkerEntity
import net.grieverc.contactlog.features.repo.room.WorkerSpecialtyCrossRef

data class SpecialtyWithWorkersUnion(
    @Embedded
    val specialty: SpecialtyEntity,
    @Relation(
        parentColumn = "specialtyId",
        entityColumn = "workerId",
        associateBy = Junction(WorkerSpecialtyCrossRef::class)
    )
    val workerList: List<WorkerEntity>
) {
    fun toModel(): List<WorkerModel> {
        val specialtyModel = specialty.toModel()
        return workerList.map {
            it.toModel(listOf(specialtyModel))
        }
    }
}