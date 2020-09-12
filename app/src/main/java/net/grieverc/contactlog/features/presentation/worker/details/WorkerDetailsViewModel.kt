package net.grieverc.contactlog.features.presentation.worker.details

import androidx.lifecycle.*
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.domain.case.WorkerProvider

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