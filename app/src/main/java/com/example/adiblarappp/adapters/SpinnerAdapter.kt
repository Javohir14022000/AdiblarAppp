package com.example.adiblarappp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.adiblarappp.databinding.ItemSpinnerBinding

class SpinnerAdapter(var list: List<String>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = ItemSpinnerBinding.inflate(LayoutInflater.from(p2?.context), p2, false)
        view.spinnerTv.text = list[p0]
        return view.root
    }

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }
}