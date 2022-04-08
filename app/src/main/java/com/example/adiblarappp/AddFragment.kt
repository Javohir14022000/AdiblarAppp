package com.example.adiblarappp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.adiblarappp.adapters.SpinnerAdapter
import com.example.adiblarappp.database.entity.Adib
import com.example.adiblarappp.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import java.util.jar.Manifest

class AddFragment : Fragment(R.layout.fragment_add) {
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    lateinit var firebaseFireStore: FirebaseFirestore
    private var imgUri: String? = null
    private lateinit var typeList: ArrayList<String>
    lateinit var list: ArrayList<Adib>
    private val binding: FragmentAddBinding by viewBinding(FragmentAddBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFireStore()
        loadData()
        loadSpinner()
        imageBtnClick()
        saveClick()

    }

    private fun saveClick() {
        binding.apply {
            saveBtn.setOnClickListener {
                val name = adibName.text.toString()
                val year = adibYear.text.toString()
                val endYear = adibYearEnd.text.toString()
                val typeSpin = typeList[typeSpinner.selectedItemPosition]
                val about = adibDesc.text.toString()

                if (name.isNotEmpty() && year.isNotEmpty() && endYear.isNotEmpty() && typeSpin.isNotEmpty() && about.isNotEmpty() && !typeSpin.equals(
                        "Turi",
                        true
                    ) && imgUri != null
                ) {

                    if (checkName(name)) {
                        val adib =
                            Adib(name, ("$year - $endYear"), typeSpin, about, imgUri!!, false)

                        firebaseFireStore.collection("adib").document(name)
                            .set(adib)
                            .addOnSuccessListener {
                                findNavController().popBackStack()
                                Snackbar.make(
                                    root,
                                    "Muvaffaqiyatli qo`shildi",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }.addOnFailureListener {
                                Snackbar.make(root, "Xatolik", Snackbar.LENGTH_LONG).show()
                            }
                    } else {
                        Snackbar.make(root, "$name oldin kritilgan qo`shildi", Snackbar.LENGTH_LONG)
                            .show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "Bo'sh o'rinlarni to'ldiring!",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }

            }
        }
    }

    private fun checkName(name: String): Boolean {
        var check = true

        for (i in 0 until list.size) {
            if (name.equals(list[i].name, true)) {
                check = false
            }
        }
        return check
    }

    private fun imageBtnClick() {
        binding.imgBtn.setOnClickListener {
            getImageContent.launch("image/*")
        }
    }

    private val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it == null) return@registerForActivityResult
        uploadStorage(it)
    }

    private fun uploadStorage(uri: Uri) {
        reference.child("${System.currentTimeMillis()}").putFile(uri)
            .addOnSuccessListener {
                if (it.task.isSuccessful) {
                    it.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                        Picasso.get().load(it.toString()).into(binding.imageAdib)
                        imgUri = it.toString()
                    }
                    Toast.makeText(requireContext(), "Success!!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), it.error?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loadSpinner() {
        typeList = ArrayList()
        typeList.add("Turi:")
        typeList.add("Mumtoz adabiyoti")
        typeList.add("O'zbek adabiyoti")
        typeList.add("Jahon adabiyoti")

        val adapter = SpinnerAdapter(typeList)
        binding.typeSpinner.adapter = adapter
    }

    private fun loadData() {
        list = ArrayList()
        firebaseFireStore.collection("adib").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        val adib = queryDocumentSnapshot.toObject(Adib::class.java)
                        list.add(adib)
                    }
                }
            }.addOnFailureListener {
                Log.d("TAG", it.message!!)
                Toast.makeText(
                    requireContext(),
                    "Firebase bilan qandaydir muammo bor, Noqulayliklar uchun uzr so`raymiz",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun loadFireStore() {
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("images/")
        firebaseFireStore = FirebaseFirestore.getInstance()


    }

}