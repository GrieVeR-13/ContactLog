package net.grieverc.contactlog.ui.specialty

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.grieverc.contactlog.repo.SpecialtyModel
import net.grieverc.contactlog.repo.ContactLogRepository
import net.grieverc.contactlog.repo.SampleData
import net.grieverc.contactlog.R

/**
 * Презентер для фрагмента со списком специальностей
 */

class SpecialtyRosterViewModel(
    private val repository: ContactLogRepository,
    private val context: Context
) : ViewModel() {
    private val TAG = "ContactLog"

    private val mediatorLiveData = MediatorLiveData<List<SpecialtyModel>>()
    val specialtyListLiveData: LiveData<List<SpecialtyModel>> = mediatorLiveData
    private var liveDataLast: LiveData<List<SpecialtyModel>>? = null

    init {
        loadAll()
    }

    fun loadAll() {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = repository.loadSpecialty().asLiveData()

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }

    fun insertSampleData() {
        viewModelScope.launch {
            repository.insert(SampleData.specialtyWithWorkersUnionList)
        }
    }

    fun importRemoteData() {
        viewModelScope.launch {
            try {
                repository.importItems(context.getString(R.string.remote_data_url_default))
            } catch (ex: Exception) {
                Log.e(TAG, "Exception: Import Remote Data", ex)
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}