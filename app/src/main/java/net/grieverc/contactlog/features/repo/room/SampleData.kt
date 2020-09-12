package net.grieverc.contactlog.features.repo.room

import net.grieverc.contactlog.features.repo.room.union.SpecialtyWithWorkersUnion
import org.threeten.bp.LocalDate

object SampleData {
    val specialtyWithWorkersUnionList: List<SpecialtyWithWorkersUnion>

    init {
        val spec1 = SpecialtyEntity(
            specialtyId = "1",
            name = "Инженер",
            description = ""
        )
        val spec2 = SpecialtyEntity(
            specialtyId = "2",
            name = "Программист",
            description = ""
        )
        val spec3 = SpecialtyEntity(
            specialtyId = "3",
            name = "Бухгалтер",
            description = ""
        )
        specialtyWithWorkersUnionList = listOf(
            SpecialtyWithWorkersUnion(
                specialty = spec1,
                workerList = listOf(
                    WorkerEntity(
                        first_name = "Иван",
                        surname = "Иванов",
                        birthDate = LocalDate.now().minusYears(21),
                        specialtyFId = spec1.specialtyId
                    ),
                    WorkerEntity(
                        first_name = "Петр",
                        surname = "Иванов",
                        birthDate = LocalDate.now().minusYears(22),
                        specialtyFId = spec1.specialtyId
                    )
                )
            ),
            SpecialtyWithWorkersUnion(
                specialty = spec2,
                workerList = listOf(
                    WorkerEntity(
                        first_name = "Петр",
                        surname = "Петров",
                        birthDate = LocalDate.now().minusYears(21),
                        specialtyFId = spec2.specialtyId
                    ),
                    WorkerEntity(
                        first_name = "Иван",
                        surname = "Петров",
                        birthDate = LocalDate.now().minusYears(22),
                        specialtyFId = spec2.specialtyId
                    )
                )
            ),
            SpecialtyWithWorkersUnion(
                specialty = spec3,
                workerList = emptyList()
            )
        )
    }
}
