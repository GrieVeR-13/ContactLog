package net.grieverc.contactlog.ui.specialty

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.grieverc.contactlog.repo.*
import net.grieverc.contactlog.repo.specialty.SpecialtyModel
import net.grieverc.contactlog.repo.specialty.toEntity

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
        val items = repository.specialtyLoad().asLiveData()

        specialtyMediatorLiveData.addSource(items) {
            specialtyMediatorLiveData.value = it
        }
        liveDataLast = items
    }

    fun insertSampleData() {
        viewModelScope.launch {
            SampleData.specialyList.forEach {
                repository.insert(it.toEntity())
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}