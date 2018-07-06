package com.digital_pusauli.view.categroy_menu

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.digital_pusauli.R
import com.digital_pusauli.view.categroy.CategoryActivity
import kotlinx.android.synthetic.main.adapter_layout_categories.view.*


class CategoryAdapter(private val list : ArrayList<CategoriModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout_categories,parent,false)
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
        private var catg_nameKey: String? = null
        fun bindItems(model: CategoriModel){
            itemView.setOnClickListener(this) // bind the listener
            catg_nameKey=model.nameKey
            catg_name=model.name
            itemView.img.setImageResource(model.img)
            itemView.txt.text=model.name
        }

        override fun onClick(v: View?) {
            context.startActivity(Intent(context, CategoryActivity::class.java)
                    .putExtra("key",catg_name)
                    .putExtra("catg_nameKey",catg_nameKey))
        }
    }

}

