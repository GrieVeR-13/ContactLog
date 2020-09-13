package net.grieverc.contactlog.features.repo.room.union

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.repo.room.SpecialtyEntity
import net.grieverc.contactlog.features.repo.room.WorkerEntity
import net.grieverc.contactlog.features.repo.room.WorkerSpecialtyCrossRef
import net.grieverc.contactlog.features.repo.room.toEntity

data class WorkerWithSpecialtysUnion(
    @Embedded
    val worker: WorkerEntity,
    @Relation(
        parentColumn = "workerId",
        entityColumn = "specialtyId",
        associateBy = Junction(WorkerSpecialtyCrossRef::class)
    )
    val specialtyList: List<SpecialtyEntity>
) {
    constructor(worker: WorkerModel) : this(
        worker.toEntity(),
        worker.specialtyList.map { it.toEntity() })

    fun toModel() = worker.toModel(specialtyList.map { it.toModel() })
}

