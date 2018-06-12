package com.digital_pusauli

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_layout_categories.view.*


class CategoryAdapter(private val list : ArrayList<CategoriModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout_categories,parent,false)
        return CategoryAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val context: Context =itemView.context
        fun bindItems(model:CategoriModel){

            itemView.img.setImageResource(model.img)
            itemView.txt.text=model.name


        }
    }

}

