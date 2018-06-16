package com.digital_pusauli.view.categroy

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.digital_pusauli.R
import com.digital_pusauli.utils.loadFromUrl

import com.digital_pusauli.model.Result
import kotlinx.android.synthetic.main.custome_adapter_medical.view.*


class CategoryAdapters(private val list : ArrayList<Result>) : RecyclerView.Adapter<CategoryAdapters.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.custome_adapter_medical,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private val context: Context =itemView.context
        private var catg_name: String? = null
        fun bindItems(model: Result){
            itemView.setOnClickListener(this) // bind the listener
           // catg_name=model.




             itemView.img.loadFromUrl(model.shopAvatar)
             itemView.logo.loadFromUrl(model.ownerAvatar)
             itemView.collage_name.text=model.shopName
             itemView.location.text=model.shopAddress
             itemView.rating.text=model.shopRating

             itemView.corse_name.text=model.shopId
             itemView.reg.text=model.shopReg

             itemView.tv_rupees.text=model.ownerName
             itemView.contact.text=model.ownerContact
        }

        override fun onClick(v: View?) {
           context.startActivity(Intent(context, CategoryActivity::class.java).putExtra("key",catg_name))
         //   Toast.makeText(context,catg_name,Toast.LENGTH_SHORT).show()
        }


    }

}

