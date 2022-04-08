package com.example.adiblarappp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.adiblarappp.databinding.FragmentSettings2Binding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class SettingsFragment : Fragment(R.layout.fragment_settings2) {
    private val binding: FragmentSettings2Binding by viewBinding(FragmentSettings2Binding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvAdd.setOnClickListener {
                findNavController().navigate(R.id.addFragment2)

            }
        }
    }

}