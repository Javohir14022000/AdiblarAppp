package com.example.adiblarappp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.adiblarappp.adapters.LiteratureAdapter
import com.example.adiblarappp.database.AppDatabase
import com.example.adiblarappp.database.dao.AdibDao
import com.example.adiblarappp.database.entity.Adib
import com.example.adiblarappp.databinding.FragmentLiteratureBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


private const val ARG_PARAM1 = "param1"


class LiteratureFragment : Fragment(R.layout.fragment_literature) {
    private var position: Int? = null
    private val binding: FragmentLiteratureBinding by viewBinding(FragmentLiteratureBinding::bind)
    lateinit var firebaseFirestore: FirebaseFirestore
    private var adapter: LiteratureAdapter? = null
    lateinit var data: ArrayList<Adib>
    lateinit var firebaseData: ArrayList<Adib>
    lateinit var appDatabase: AppDatabase
    lateinit var getDao: AdibDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            firebaseFirestore = FirebaseFirestore.getInstance()
            appDatabase = AppDatabase.getInstance(requireContext())

            loadData()
            saveClick()
            itemClick()
            adapter = LiteratureAdapter(requireContext(), data)

            binding.rv.adapter = adapter

        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
        saveClick()
        itemClick()
    }

    private fun saveClick() {
        adapter?.setOnSaveClick(object : LiteratureAdapter.OnSaveClick {
            override fun onClick(adib: Adib, position: Int, imageView: ImageView) {


                if (adib.isSaved == true) {
                    data[position] = firebaseData[position]
                    data[position].isSaved = false
                    adib.id?.let { appDatabase.adibDao().deleteAdib(it) }

                    adapter?.notifyItemChanged(position)
                } else {
                    data[position].isSaved = true
                    appDatabase.adibDao().addAdib(adib)
                    data[position] = appDatabase.adibDao().getAllAdib().last()

                    adapter?.notifyItemChanged(position)
                }
            }

        })

    }

    private fun itemClick() {
        adapter?.setOnItemClick(object : LiteratureAdapter.OnItemClick {
            override fun onClick(adib: Adib, position: Int) {
                val navOptions = NavOptions.Builder()
                navOptions.setEnterAnim(R.anim.exit_anim)
                navOptions.setExitAnim(R.anim.pop_enter_anim)
//                navOptions.setPopEnterAnim(R.anim.pop_enter_anim)
//                navOptions.setPopExitAnim(R.anim.pop_exit_anim)

                val bundle = Bundle()
                bundle.putSerializable("adib", adib)
                findNavController().navigate(R.id.infoItemFragment, bundle, navOptions.build())
            }

        })
    }

    private fun loadData() {
        data = ArrayList()
        firebaseData = ArrayList()
        Log.d("TAG", data.size.toString())


        firebaseFirestore.collection("adib").get().addOnCompleteListener {
            if (it.isSuccessful) {

                val result = it.result

                data.clear()
                firebaseData.clear()

                result?.forEach { queryDocumentSnapshot ->
                    val adib = queryDocumentSnapshot.toObject(Adib::class.java)
                    Log.d("TAG", adib.type)
                    when (position) {
                        0 -> {
                            if (adib.type.equals("Mumtoz adabiyoti", true)) {
                                data.add(adib)
                                firebaseData.add(adib)
                            }
                        }
                        1 -> {
                            if (adib.type.equals("O'zbek adabiyoti", true)) {
                                data.add(adib)
                                firebaseData.add(adib)
                            }
                        }
                        2 -> {
                            if (adib.type.equals("Jahon adabiyoti", true)) {
                                data.add(adib)
                                firebaseData.add(adib)
                            }
                        }
                    }
                }
                data = mergeData(data, appDatabase.adibDao().getAllAdib() as ArrayList<Adib>)
                adapter?.notifyDataSetChanged()
            }
        }.addOnFailureListener {

        }
    }

    private fun mergeData(
        firebaseData: ArrayList<Adib>,
        roomData: List<Adib>,
    ): ArrayList<Adib> {
        for (i in 0 until firebaseData.size) {
            for (j in 0 until roomData.size) {
                if (firebaseData[i].name.equals(roomData[j].name, true)) {
                    roomData[j].isSaved = true
                    firebaseData[i] = roomData[j]
                }
            }
        }

//        appDatabase.adibDao().getAllAdibFlowable()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                adapter?.filterList(it)
//            }
        return firebaseData

    }

    companion object {
        fun newInstance(position: Int) =
            LiteratureFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, position)
                }
            }
    }
}