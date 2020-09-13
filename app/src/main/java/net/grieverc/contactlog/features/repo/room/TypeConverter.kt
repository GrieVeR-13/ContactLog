package net.grieverc.contactlog.features.repo.room

import androidx.room.TypeConverter
import net.grieverc.contactlog.core.service.FormatterService
import org.threeten.bp.LocalDate

object DateTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toLocalDate(dateString: String?) =
        dateString?.let {
            LocalDate.parse(it, FormatterService.C_DateTimeFormatterDefault)
        }

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(FormatterService.C_DateTimeFormatterDefault)
    }

}