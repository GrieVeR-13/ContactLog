package net.grieverc.contactlog.repo

import androidx.room.Embedded
import androidx.room.Relation
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity

data class SpecialtyWithWorkerListModel(
    val specialty: SpecialtyModel,
    val workerList: List<WorkerModel>
)
