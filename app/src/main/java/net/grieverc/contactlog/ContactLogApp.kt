package net.grieverc.contactlog

import android.app.Application
import net.grieverc.contactlog.repo.ContactLogDatabase
import net.grieverc.contactlog.repo.ContactLogRepository
import net.grieverc.contactlog.ui.specialty.SpecialtyRosterViewModel
import net.grieverc.contactlog.ui.worker.WorkerRosterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ContactLogApp : Application() {
    private val koinModule = module {
        viewModel { SpecialtyRosterViewModel(get()) }
        viewModel { WorkerRosterViewModel(get()) }
        single { ContactLogRepository(get(), get()) }
        single { ContactLogDatabase.newInstance(androidContext()) }
        single { get<ContactLogDatabase>().specialtyEntityStore() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ContactLogApp)
            modules(koinModule)
        }
    }
}