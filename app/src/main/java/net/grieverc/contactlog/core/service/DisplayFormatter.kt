package net.grieverc.contactlog.core.service

import java.util.*

class DisplayFormatter {
    companion object {
        fun formatName(name: String) =
            if (name.isNotEmpty()) {
                name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1).toLowerCase(Locale.ROOT)
            } else
                name

    }
}