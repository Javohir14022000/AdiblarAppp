package com.example.adiblarappp.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblarappp.R
import com.example.adiblarappp.database.entity.Adib
import com.example.adiblarappp.databinding.ItemWriterBinding
import com.squareup.picasso.Picasso

class LiteratureAdapter(val context: Context, var list: ArrayList<Adib>) :
    RecyclerView.Adapter<LiteratureAdapter.Vh>() {

    private var onItemClick: OnItemClick? = null
    private var onSaveClick: OnSaveClick? = null

    inner class Vh(var itemWriterBinding: ItemWriterBinding) :
        RecyclerView.ViewHolder(itemWriterBinding.root) {

        fun onBind(adib: Adib, position: Int) {
            itemWriterBinding.apply {
                nameWriters.text = adib.name
                tvYear.text = adib.years
                Picasso.get().load(adib.image).into(itemWriterBinding.imageBook)
                if (adib.isSaved == true) {
                    save.setBackgroundResource(R.drawable.saved_btn_back)
                    saveIv.setImageResource(R.drawable.save)
                } else {
                    save.setBackgroundResource(R.drawable.unsave_back)
                    saveIv.setImageResource(R.drawable.save_defoult)
                }

                itemView.setOnClickListener {
                    onItemClick?.onClick(adib, position)
                }

                saveIv.setOnClickListener {
                    if (onSaveClick != null) {
                        onSaveClick?.onClick(
                            adib,
                            position,
                            itemWriterBinding.saveIv
                        )
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemWriterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClick {
        fun onClick(adib: Adib, position: Int)
    }

    interface OnSaveClick {
        fun onClick(adib: Adib, position: Int, imageView: ImageView)
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    fun setOnSaveClick(onSaveClick: OnSaveClick) {
        this.onSaveClick = onSaveClick
    }

//    fun filterList(filteredList: List<Adib>) {
//        list = filteredList as ArrayList<Adib>
//        notifyDataSetChanged()
//    }
}