package com.pandit.application.productflavorspractice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pandit.application.productflavorspractice.ui.main.MainFragment
import kotlinx.android.synthetic.freeVersion.main_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
///*different build value from gradle*/
//        message.setOnClickListener {
//            val intent = Intent(this@MainActivity, HomeActivity::class.java)
//            startActivity(intent)
//        }

    }
}
