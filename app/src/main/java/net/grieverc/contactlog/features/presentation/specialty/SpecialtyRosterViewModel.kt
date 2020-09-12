package net.grieverc.contactlog.features.presentation.specialty

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.grieverc.contactlog.R
import net.grieverc.contactlog.features.domain.case.ContactLogImporter
import net.grieverc.contactlog.features.domain.case.SpecialtyProvider
import net.grieverc.contactlog.features.presentation.view.SpecialtyView
import net.grieverc.contactlog.features.presentation.view.toView
import net.grieverc.contactlog.features.repo.room.SampleData

class SpecialtyRosterViewModel(
    private val specialtyProvider: SpecialtyProvider,
    private val contactLogImporter: ContactLogImporter,
    private val context: Context
) : ViewModel() {

    private val mediatorLiveData = MediatorLiveData<List<SpecialtyView>>()
    val specialtyListLiveData: LiveData<List<SpecialtyView>> = mediatorLiveData
    private var liveDataLast: LiveData<List<SpecialtyView>>? = null

    init {
        load()
    }

    private fun load() {
        liveDataLast?.let { mediatorLiveData.removeSource(it) }
        val items = specialtyProvider.getSpecialty().map {
            it.map { specialtyModel ->
                specialtyModel.toView()
            }
        }.asLiveData()

        mediatorLiveData.addSource(items) {
            mediatorLiveData.value = it
        }
        liveDataLast = items
    }

    fun insertSampleData() {
        viewModelScope.launch {
            specialtyProvider.save(SampleData.specialtyWithWorkersUnionList)
        }
    }

    fun importRemoteData() {
        viewModelScope.launch {
            contactLogImporter.import(context.getString(R.string.remote_data_url_default))
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            specialtyProvider.clearAll()
        }
    }
}