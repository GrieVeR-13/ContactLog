package net.grieverc.contactlog.repo.room.view

import androidx.room.Embedded
import net.grieverc.contactlog.repo.room.SpecialtyEntity
import net.grieverc.contactlog.repo.room.WorkerEntity

data class WorkerWithSpecialtyEntity(
    @Embedded val worker: WorkerEntity,
    @Embedded val specialty: SpecialtyEntity
) {

}
