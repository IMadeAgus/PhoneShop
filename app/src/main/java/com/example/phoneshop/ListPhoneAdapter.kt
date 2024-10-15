package com.example.phoneshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.phoneshop.databinding.ItemRowPhoneBinding
import java.text.NumberFormat
import java.util.Locale


class ListPhoneAdapter(private val listPhone: ArrayList<Phone>) : RecyclerView.Adapter<ListPhoneAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ListViewHolder(var binding: ItemRowPhoneBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowPhoneBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPhone.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, description, photo) = listPhone[position]
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemPrice.text =  formatRupiah.format(price)
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPhone[holder.adapterPosition]) }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Phone)
    }

}