package net.grieverc.contactlog.features.presentation.worker

import androidx.lifecycle.*
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.domain.case.WorkerProvider

class WorkerRosterViewModel(private val workerProvider: WorkerProvider, specialtyId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<List<WorkerModel>>()
    val workerListLiveData: LiveData<List<WorkerModel>> = mediatorLiveData
    private var liveDataLast: LiveData<List<WorkerModel>>? = null

    init {
        loadById(specialtyId)
    }

    private fun loadById(specialtyId: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = workerProvider.getWorkerList(specialtyId).asLiveData()

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}