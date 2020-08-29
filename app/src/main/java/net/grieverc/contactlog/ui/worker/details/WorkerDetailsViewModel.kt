package net.grieverc.contactlog.ui.worker

import androidx.lifecycle.*
import net.grieverc.contactlog.repo.WorkerModel
import net.grieverc.contactlog.repo.room.ContactLogRepository

/**
 * Презентер для фрагмента со списком работников
 */

class WorkerDetailsViewModel(private val repository: ContactLogRepository, val workerId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<WorkerModel>()
    val workerLiveData: LiveData<WorkerModel> = mediatorLiveData
    private var liveDataLast: LiveData<WorkerModel>? = null

    init {
        loadById(workerId)
    }

    fun loadById(id: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = repository.loadWorkerById(id).asLiveData()
        val asd = repository.globalDao.loadWorkerWithSpecialtyById(id)

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}