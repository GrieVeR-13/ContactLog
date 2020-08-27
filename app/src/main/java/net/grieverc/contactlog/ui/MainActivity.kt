package net.grieverc.contactlog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import net.grieverc.contactlog.R
import net.grieverc.contactlog.databinding.ActivityMainBinding
import net.grieverc.contactlog.ui.specialty.SpecialtyRosterFragment
import net.grieverc.contactlog.ui.worker.WorkerRosterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SpecialtyRosterFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.test1 -> {
                println("asdasd")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}