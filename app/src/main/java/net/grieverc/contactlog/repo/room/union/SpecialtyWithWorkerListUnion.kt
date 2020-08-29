package net.grieverc.contactlog.repo.room.union

import androidx.room.Embedded
import androidx.room.Relation
import net.grieverc.contactlog.repo.WorkerModel
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity

data class SpecialtyWithWorkerListUnion(
    @Embedded val specialty: SpecialtyEntity,
    @Relation(
        parentColumn = "specialtyId",
        entityColumn = "specialtyFId"
    )
    val workerList: List<WorkerEntity>
) {
    fun toModel(): List<WorkerModel> {
        val specialtyModel = specialty.toModel()
        return workerList.map {
            it.toModel().apply { specialty = specialtyModel }
        }
    }
}
