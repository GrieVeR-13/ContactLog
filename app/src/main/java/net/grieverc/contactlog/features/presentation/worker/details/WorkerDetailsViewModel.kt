package net.grieverc.contactlog.features.presentation.worker.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import net.grieverc.contactlog.features.domain.case.WorkerProvider
import net.grieverc.contactlog.features.presentation.view.WorkerView
import net.grieverc.contactlog.features.presentation.view.toView

class WorkerDetailsViewModel(private val workerProvider: WorkerProvider, val workerId: String) : ViewModel() {
    private val mediatorLiveData = MediatorLiveData<WorkerView?>()
    val workerLiveData: LiveData<WorkerView?> = mediatorLiveData
    private var liveDataLast: LiveData<WorkerView?>? = null

    init {
        loadById(workerId)
    }

    private fun loadById(workerId: String) {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = workerProvider.getWorker(workerId).map {
            it?.toView()
        }.asLiveData()
        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }
}