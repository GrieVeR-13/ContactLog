package net.grieverc.contactlog.features.presentation.worker

import androidx.lifecycle.*
import kotlinx.coroutines.flow.map
import net.grieverc.contactlog.features.domain.model.WorkerModel
import net.grieverc.contactlog.features.domain.case.WorkerProvider
import net.grieverc.contactlog.features.presentation.view.WorkerView
import net.grieverc.contactlog.features.presentation.view.toView

class WorkerRosterViewModel(private val workerProvider: WorkerProvider, specialtyId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<List<WorkerView>>()
    val workerListLiveData: LiveData<List<WorkerView>> = mediatorLiveData
    private var liveDataLast: LiveData<List<WorkerView>>? = null

    init {
        loadById(specialtyId)
    }

    private fun loadById(specialtyId: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = workerProvider.getWorkerList(specialtyId).map {
            it.map { workerModel ->
                workerModel.toView()
            }
        }.asLiveData()

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}


