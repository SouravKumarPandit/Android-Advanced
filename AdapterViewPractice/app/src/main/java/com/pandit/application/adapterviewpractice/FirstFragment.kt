package com.pandit.application.adapterviewpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.StackView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pandit.application.adapterviewpractice.stackview.StackViewAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        val sv: StackView = view.findViewById(R.id.stackView)

        sv.setInAnimation(activity, android.R.animator.fade_in)
        sv.setOutAnimation(activity, android.R.animator.fade_out)

        val albumsAdapter = StackViewAdapter(
            getStores(), R.layout.layout_stack_item,
            activity
        )

        sv.adapter = albumsAdapter
    }
    private fun getStores(): List<String>? {
        val stores: MutableList<String> = ArrayList()
        stores.add("apple")
        stores.add("htc")
        stores.add("samsung")
        stores.add("lg")
        stores.add("sony")
        stores.add("nokia")
        stores.add("panasonic")
        return stores
    }
}
