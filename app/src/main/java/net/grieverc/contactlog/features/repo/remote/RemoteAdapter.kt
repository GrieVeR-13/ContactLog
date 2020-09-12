package net.grieverc.contactlog.features.repo.remote

import com.squareup.moshi.FromJson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class MoshiLocalDateAdapter {
    private val formatter = DateTimeFormatter.ofPattern(
        "[yyyy-MM-dd]" +
                "[dd-MM-yyyy]"
    )

    @FromJson
    fun fromJson(dateString: String): LocalDate? =
        if (dateString.isNotEmpty()) LocalDate.parse(dateString, formatter) else null
}
