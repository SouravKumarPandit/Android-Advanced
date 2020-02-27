package com.pandit.application.productflavorspractice

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout:LinearLayout= LinearLayout(this)
        layout.layoutParams= ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
        layout.setBackgroundColor(Color.RED)
        setContentView(layout)

    }
}
