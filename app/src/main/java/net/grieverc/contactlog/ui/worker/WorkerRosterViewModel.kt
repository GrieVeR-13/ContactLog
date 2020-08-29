package net.grieverc.contactlog.ui.worker

import androidx.lifecycle.*
import net.grieverc.contactlog.repo.WorkerModel
import net.grieverc.contactlog.repo.room.ContactLogRepository

/**
 * Презентер для фрагмента со списком работников
 */

class WorkerRosterViewModel(private val repository: ContactLogRepository, val specialtyId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<List<WorkerModel>>()
    val workerListLiveData: LiveData<List<WorkerModel>> = mediatorLiveData
    private var liveDataLast: LiveData<List<WorkerModel>>? = null

    init {
        loadById(specialtyId)
    }

    fun loadById(id: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = repository.loadWorkerListBySpecialtyId(id).asLiveData()

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}