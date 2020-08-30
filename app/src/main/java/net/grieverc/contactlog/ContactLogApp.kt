package net.grieverc.contactlog

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import net.grieverc.contactlog.repo.room.ContactLogDatabase
import net.grieverc.contactlog.repo.ContactLogRepository
import net.grieverc.contactlog.repo.remote.ContactLogRemoteData
import net.grieverc.contactlog.ui.specialty.SpecialtyRosterViewModel
import net.grieverc.contactlog.ui.worker.WorkerDetailsViewModel
import net.grieverc.contactlog.ui.worker.WorkerRosterViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ContactLogApp : Application() {
    private val koinModule = module {
        viewModel { SpecialtyRosterViewModel(get(), androidContext()) }
        viewModel { (specialtyId: String) ->
            WorkerRosterViewModel(get(), specialtyId)
        }
        viewModel { (workerId: String) ->
            WorkerDetailsViewModel(get(), workerId)
        }
        single {
            ContactLogRepository(
                get(),
                get(),
                get()
            )
        }
        single { ContactLogDatabase.newInstance(androidContext()) }
        single { get<ContactLogDatabase>().globalDao() }
        single { ContactLogRemoteData(get()) }
        single { OkHttpClient.Builder().build() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ContactLogApp)
            modules(koinModule)
        }
        AndroidThreeTen.init(this)
    }
}