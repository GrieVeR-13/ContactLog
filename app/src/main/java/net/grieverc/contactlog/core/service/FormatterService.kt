package net.grieverc.contactlog.core.service

import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class FormatterService {
    companion object {
        fun formatName(name: String) =
            if (name.isNotEmpty()) {
                name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1).toLowerCase(Locale.ROOT)
            } else
                name

       val C_DateTimeFormatterDefault = DateTimeFormatter.ofPattern("[dd.MM.yyyy]")
    }
}

