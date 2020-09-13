package net.grieverc.contactlog.core.di

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import net.grieverc.contactlog.features.domain.case.ContactLogImporter
import net.grieverc.contactlog.features.domain.case.SpecialtyProvider
import net.grieverc.contactlog.features.domain.case.WorkerProvider
import net.grieverc.contactlog.features.presentation.specialty.SpecialtyRosterViewModel
import net.grieverc.contactlog.features.repo.room.ContactLogRepository
import net.grieverc.contactlog.features.repo.room.Repository
import net.grieverc.contactlog.features.repo.remote.ContactLogRemoteService
import net.grieverc.contactlog.features.repo.room.ContactLogDatabase
import net.grieverc.contactlog.features.presentation.worker.details.WorkerDetailsViewModel
import net.grieverc.contactlog.features.presentation.worker.WorkerRosterViewModel
import net.grieverc.contactlog.features.repo.remote.ContactLogRemoteRepository
import net.grieverc.contactlog.features.repo.remote.RemoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

object KoinModule {
    private const val applicationScope = "applicationScope"

    private val instance = module {
        viewModel {
            SpecialtyRosterViewModel(get(), get(), androidContext())
        }
        viewModel { (specialtyId: String) ->
            WorkerRosterViewModel(get(), specialtyId)
        }
        viewModel { (workerId: String) ->
            WorkerDetailsViewModel(get(), workerId)
        }
        single { SpecialtyProvider(get()) }
        single { ContactLogImporter(get(), get()) }
        single { WorkerProvider(get()) }
        single {
            ContactLogRepository(
                get(),
                get(),
                get(named(applicationScope))
            ) as Repository
        }
        single { ContactLogDatabase.newInstance(androidContext()) }
        single { get<ContactLogDatabase>().globalDao() }
        single {
            ContactLogRemoteRepository(
                get()
            ) as RemoteRepository
        }
        single { ContactLogRemoteService(get()) }
        single { ContactLogRemoteService.retrofitNewInstance(androidContext()) }
        single(named(applicationScope)) { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    }

    fun start(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(instance)
        }
    }
}

