package net.grieverc.contactlog.features.domain.model

import org.threeten.bp.LocalDate

object SampleData {
    val workerList: List<WorkerModel>

    init {
        val spec1 = SpecialtyModel(
            id = "1",
            name = "Инженер",
            description = ""
        )
        val spec2 = SpecialtyModel(
            id = "2",
            name = "Программист",
            description = ""
        )
        workerList = listOf(
            WorkerModel(
                id = "1",
                firstName = "Иван",
                surname = "Иванов",
                birthDate = LocalDate.now().minusYears(21),
                specialty = spec1
            ),
            WorkerModel(
                id = "2",
                firstName = "Петр",
                surname = "Иванов",
                birthDate = LocalDate.now().minusYears(22),
                specialty = spec1
            ),
            WorkerModel(
                id = "3",
                firstName = "Петр",
                surname = "Петров",
                birthDate = LocalDate.now().minusYears(21),
                specialty = spec2
            ),
            WorkerModel(
                id = "4",
                firstName = "Иван",
                surname = "Петров",
                birthDate = LocalDate.now().minusYears(22),
                specialty = spec2
            )
        )
    }
}
