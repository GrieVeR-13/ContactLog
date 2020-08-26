package net.grieverc.contactlog.repo

class ContactLogRepository(val specialtyEntityStore: SpecialtyEntity.Store) {
    val specialyList = mutableListOf(
        SpecialtyModel(name = "n1", description = "d1"),
        SpecialtyModel(name = "n2", description = "d2")
    )

    fun load() {
        val a= specialtyEntityStore.loadAll().map {
            it.toModel()
        }
        specialyList.addAll(a)
    }

    fun insert() {
        specialtyEntityStore.insert(SpecialtyEntity(specialyList[0]))
    }
}