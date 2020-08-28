package net.grieverc.contactlog.ui.specialty

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.grieverc.contactlog.repo.SpecialtyModel
import net.grieverc.contactlog.repo.room.ContactLogRepository
import net.grieverc.contactlog.repo.room.SampleData

/**
 * Презентер для фрагмента со списком специальностей
 */

class SpecialtyRosterViewModel(private val repository: ContactLogRepository) : ViewModel() {
    private val specialtyMediatorLiveData = MediatorLiveData<List<SpecialtyModel>>()
    val specialtyLiveData: LiveData<List<SpecialtyModel>> = specialtyMediatorLiveData
    private var liveDataLast: LiveData<List<SpecialtyModel>>? = null

    init {
        loadAll()
    }

    fun loadAll() {
        liveDataLast?.let { specialtyMediatorLiveData.removeSource(it) }
        val items = repository.loadSpecialty().asLiveData()

        specialtyMediatorLiveData.addSource(items) {
            specialtyMediatorLiveData.value = it
        }
        liveDataLast = items
    }

    fun insertSampleData() {
        viewModelScope.launch {
            SampleData.specialtyWithWorkerList.forEach {
                repository.insert(it)
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}