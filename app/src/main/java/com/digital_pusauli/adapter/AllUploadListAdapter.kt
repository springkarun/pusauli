package com.digital_pusauli.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digital_pusauli.R
import com.digital_pusauli.app.Constant.BASE_URL_Image
import com.digital_pusauli.model.Data
import com.digital_pusauli.utils.loadFromUrl
import com.digital_pusauli.utils.timeStamp
import kotlinx.android.synthetic.main.adapter_all_upload_list_layout.view.*

class AllUploadListAdapter(private val list: ArrayList<Data>) : RecyclerView.Adapter<AllUploadListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_all_upload_list_layout, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(model:Data){

           itemView.picture.loadFromUrl(BASE_URL_Image+model.shopAvatar)
           itemView.user_name.text=model.shopName
           itemView.content.text=model.ownerName
           itemView.reg_name.text=model.shopId
           itemView.time.text=timeStamp(model.regTimeStamp!!)


        }
    }
}

