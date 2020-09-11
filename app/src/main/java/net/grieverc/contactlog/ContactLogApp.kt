package net.grieverc.contactlog

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import net.grieverc.contactlog.core.case.ContactLogImporter
import net.grieverc.contactlog.core.case.SpecialtyProvider
import net.grieverc.contactlog.core.case.WorkerProvider
import net.grieverc.contactlog.repo.room.ContactLogDatabase
import net.grieverc.contactlog.repo.ContactLogRepository
import net.grieverc.contactlog.repo.Repository
import net.grieverc.contactlog.repo.remote.ContactLogRemoteData
import net.grieverc.contactlog.ui.specialty.SpecialtyRosterViewModel
import net.grieverc.contactlog.ui.worker.WorkerDetailsViewModel
import net.grieverc.contactlog.ui.worker.WorkerRosterViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class ContactLogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinModule.start(this)
        AndroidThreeTen.init(this)
    }
}