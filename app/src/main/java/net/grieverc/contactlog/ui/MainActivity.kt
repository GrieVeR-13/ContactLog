package net.grieverc.contactlog.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import net.grieverc.contactlog.R
import net.grieverc.contactlog.databinding.ActivityMainBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.findFragmentById(R.id.navigation_host)?.findNavController()?.let {
            appBarConfiguration = AppBarConfiguration(it.graph)
            setupActionBarWithNavController(it, appBarConfiguration)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp() =
        NavigationUI.navigateUp(findNavController(R.id.navigation_host), appBarConfiguration)
}

var formatStrings = Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy")

fun tryParse(dateString: String?): Date? {
    for (formatString in formatStrings) {
        try {
            return SimpleDateFormat(formatString).parse(dateString)
        } catch (e: ParseException) {
        }
    }
    return null
}
fun main() {

//    val aa = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse("23-07-2000 00:00:00")
//    val a = tryParse("1990-01-07")

    val formatter = DateTimeFormatter.ofPattern(
        "[yyyy-MM-dd]" +
                "[dd-MM-yyyy]"
    )
    val a = LocalDate.parse("17-10-1984", formatter);
    val b = LocalDate.parse("1990-01-07", formatter);
////    val dd = Date.from(a.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
//    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
//    val output = formatter.format(parser.parse("2018-12-14T09:55:00"))
//
//    val dateFormat = SimpleDateFormat()
//    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
//
//    val date = dateFormat.parse("1985-11-29");
}