package net.grieverc.contactlog.repo.room

import android.content.Context
import androidx.room.*

private const val C_DatabaseName_ContactLog = "ContactLog.db"

@Database(entities = [SpecialtyEntity::class, WorkerEntity::class], version = 1)
abstract class ContactLogDatabase : RoomDatabase() {
    abstract fun globalDao(): GlobalDao

    companion object {
        fun newInstance(context: Context) = Room.databaseBuilder(context, ContactLogDatabase::class.java,
            C_DatabaseName_ContactLog
        ).allowMainThreadQueries().build()
    }
}