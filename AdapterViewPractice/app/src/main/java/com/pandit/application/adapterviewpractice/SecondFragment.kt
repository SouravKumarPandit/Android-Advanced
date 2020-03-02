package com.pandit.application.adapterviewpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pandit.application.adapterviewpractice.stackview.StackViewAdapter
import kotlinx.android.synthetic.main.fragment_second.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutScrollView

        view.findViewById<ScrollView>(R.id.layoutScrollView).addView(getLinearLayout())


        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun getLinearLayout(): View? {
        val layout = LinearLayout(activity)
        layout.orientation = LinearLayout.VERTICAL
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500)
        layout.addView(getStackViewAdapter(), layoutParams)
        layout.addView(getTextSwitcher(), layoutParams)


        return layout

    }

    private fun getTextSwitcher(): View? {
        val linearLayout = LinearLayout(activity)
        linearLayout.orientation = LinearLayout.VERTICAL

        val textSwitcher = TextSwitcher(activity)

        linearLayout.addView(textSwitcher)
        textSwitcher.setCurrentText("Hello Android App Developer")

        val textAnimationIn: Animation =
            AnimationUtils.loadAnimation(activity, android.R.anim.fade_in)
        textAnimationIn.setDuration(800)

        val textAnimationOut: Animation =
            AnimationUtils.loadAnimation(activity, android.R.anim.fade_out)
        textAnimationOut.setDuration(800)

        textSwitcher.inAnimation = textAnimationIn
        textSwitcher.outAnimation = textAnimationOut
        textSwitcher.setOnClickListener {
            if (count % 2 == 0) {
                textSwitcher.setText("Learn android with examples")
            } else {
                textSwitcher.setText("Welcome to android Practice")
            }
        }

        return textSwitcher

    }


    private fun getStackViewAdapter(): View? {
        val sv = StackView(activity)


        sv.setInAnimation(activity, android.R.animator.fade_in)
        sv.setOutAnimation(activity, android.R.animator.fade_out)

        val albumsAdapter = StackViewAdapter(
            getStores(), R.layout.layout_stack_item,
            activity
        )

        sv.adapter = albumsAdapter
        return sv
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
