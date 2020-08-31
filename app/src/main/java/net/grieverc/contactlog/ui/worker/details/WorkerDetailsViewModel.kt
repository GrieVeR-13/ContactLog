package net.grieverc.contactlog.ui.worker

import androidx.lifecycle.*
import net.grieverc.contactlog.core.WorkerModel
import net.grieverc.contactlog.core.case.WorkerProvider

/**
 * Презентер для фрагмента с информацией о работанике
 */

class WorkerDetailsViewModel(private val workerProvider: WorkerProvider, val workerId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<WorkerModel?>()
    val workerLiveData: LiveData<WorkerModel?> = mediatorLiveData
    private var liveDataLast: LiveData<WorkerModel?>? = null

    init {
        loadById(workerId)
    }

    private fun loadById(workerId: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = workerProvider.getWorker(workerId).asLiveData()
        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}