package com.example.adiblarappp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adiblarappp.databinding.FragmentLibraryBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class LibraryFragment : Fragment(R.layout.fragment_library) {
    private val binding: FragmentLibraryBinding by viewBinding(FragmentLibraryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}