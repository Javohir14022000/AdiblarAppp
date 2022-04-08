package com.example.adiblarappp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.adiblarappp.databinding.FragmentSettings2Binding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class SaveFragment : Fragment(R.layout.fragment_save) {
    private val binding: FragmentSettings2Binding by viewBinding(FragmentSettings2Binding::bind)
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}