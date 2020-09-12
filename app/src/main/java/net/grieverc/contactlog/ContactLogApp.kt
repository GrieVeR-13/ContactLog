package net.grieverc.contactlog

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import net.grieverc.contactlog.core.di.KoinModule

class ContactLogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinModule.start(this)
        AndroidThreeTen.init(this)
    }
}