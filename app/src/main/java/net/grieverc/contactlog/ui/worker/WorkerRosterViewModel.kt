package net.grieverc.contactlog.ui.worker

import androidx.lifecycle.*
import net.grieverc.contactlog.repo.SpecialtyWithWorkerListModel
import net.grieverc.contactlog.repo.room.ContactLogRepository

/**
 * Презентер для фрагмента со списком работников
 */

class WorkerRosterViewModel(private val repository: ContactLogRepository, val specialtyId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<SpecialtyWithWorkerListModel>()
    val specialtyWithWorkerListLiveData: LiveData<SpecialtyWithWorkerListModel> = mediatorLiveData
    private var liveDataLast: LiveData<SpecialtyWithWorkerListModel>? = null

    init {
        loadById(specialtyId)
    }

    fun loadById(id: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = repository.loadSpecialtyWithWorkerListById(id).asLiveData()

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}