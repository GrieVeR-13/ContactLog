package net.grieverc.contactlog.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.grieverc.contactlog.repo.specialty.SpecialtyEntity

private const val C_DatabaseName_ContactLog = "ContactLog.db"

@Database(entities = [SpecialtyEntity::class], version = 1)
abstract class ContactLogDatabase : RoomDatabase() {
    abstract fun specialtyEntityStore(): SpecialtyEntity.Store

    companion object {
        fun newInstance(context: Context) = Room.databaseBuilder(context, ContactLogDatabase::class.java, C_DatabaseName_ContactLog).allowMainThreadQueries().build()
    }
}