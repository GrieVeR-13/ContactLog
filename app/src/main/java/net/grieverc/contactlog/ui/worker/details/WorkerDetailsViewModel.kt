package net.grieverc.contactlog.ui.worker

import androidx.lifecycle.*
import net.grieverc.contactlog.repo.WorkerModel
import net.grieverc.contactlog.repo.ContactLogRepository

/**
 * Презентер для фрагмента с информацией о работанике
 */

class WorkerDetailsViewModel(private val repository: ContactLogRepository, val workerId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<WorkerModel?>()
    val workerLiveData: LiveData<WorkerModel?> = mediatorLiveData
    private var liveDataLast: LiveData<WorkerModel?>? = null

    init {
        loadById(workerId)
    }

    fun loadById(id: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = repository.loadWorkerById(id).asLiveData()
        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}