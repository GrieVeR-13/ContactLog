package net.grieverc.contactlog.repo.room.union

import androidx.room.Embedded
import androidx.room.Relation
import net.grieverc.contactlog.core.WorkerModel
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity
import net.grieverc.contactlog.repo.room.toEntity

data class SpecialtyWithWorkersUnion(
    @Embedded val specialty: SpecialtyEntity,
    @Relation(
        parentColumn = "specialtyId",
        entityColumn = "specialtyFId"
    )
    var workerList: List<WorkerEntity>
) {

    fun toModel(): List<WorkerModel> {
        val specialtyModel = specialty.toModel()
        return workerList.map {
            it.toModel().apply { specialty = specialtyModel }
        }
    }

    companion object {
        fun fromModelList(workerList: List<WorkerModel>): List<SpecialtyWithWorkersUnion> {
            val map = HashMap<String, SpecialtyWithWorkersUnion>()
            workerList.forEach {
                val specialty = it.specialty
                if (specialty != null) {
                    val specialtyWithWorkerListUnion = map.getOrPut(specialty.id) {
                        SpecialtyWithWorkersUnion(specialty.toEntity(), listOf())
                    }
                    specialtyWithWorkerListUnion.workerList += it.toEntity()
                }
            }
            return map.values.toList()
        }
    }
}

