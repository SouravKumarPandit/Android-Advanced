package com.pandit.application.productflavorspractice.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pandit.application.productflavorspractice.BuildConfig
import com.pandit.application.productflavorspractice.HomeActivity
import com.pandit.application.productflavorspractice.R
import kotlinx.android.synthetic.freeVersion.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        message.setOnClickListener { view->
            Toast.makeText(activity,"Paid URL :- ${BuildConfig.BASE_URL}",Toast.LENGTH_LONG).show()
            activity?.startActivity(Intent(activity,HomeActivity::class.java))
        }

        // TODO: Use the ViewModel
    }

}
