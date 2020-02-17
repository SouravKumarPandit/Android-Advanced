package com.pandit.application.servicespractice

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pandit.application.servicespractice.service.AStartService
import com.pandit.application.servicespractice.service.MyIntentService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var intent: Intent = Intent(this, AStartService::class.java)
            startService(intent)
        }


        btnStartService.setOnClickListener { view ->
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
            var intent: Intent = Intent(this, AStartService::class.java)
            startService(intent)


        }

        btnStartService.setOnClickListener {
            var intent: Intent = Intent(this, AStartService::class.java)
            stopService(intent)

        }
        btnIntentService.setOnClickListener {
            var intent: Intent = Intent(this, MyIntentService::class.java)
            stopService(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
