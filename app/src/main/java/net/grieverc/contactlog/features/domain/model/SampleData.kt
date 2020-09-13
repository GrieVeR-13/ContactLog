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
                specialtyList = listOf(spec1)
            ),
            WorkerModel(
                id = "2",
                firstName = "Петр",
                surname = "Петров",
                birthDate = LocalDate.now().minusYears(22),
                specialtyList = listOf(spec1)
            ),
            WorkerModel(
                id = "3",
                firstName = "Сергей",
                surname = "Сергеев",
                birthDate = LocalDate.now().minusYears(21),
                specialtyList = listOf(spec2)
            ),
            WorkerModel(
                id = "4",
                firstName = "Павел",
                surname = "Павлов",
                birthDate = LocalDate.now().minusYears(22),
                specialtyList = listOf(spec1, spec2)
            )
        )
    }
}
