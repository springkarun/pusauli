package com.digital_pusauli.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.digital_pusauli.R
import com.digital_pusauli.model.Data
import kotlinx.android.synthetic.main.adapter_category_all_layout.view.*

class SpinnerAdapter(private val context:Context, private val list:ArrayList<Data>) : BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

      val view:View=View.inflate(context, R.layout.adapter_category_all_layout,null)

         view.spinner_tv.text = ("Select Category")
         view.spinner_tv.text = (list[p0].categoryName)
        return view
    }

    override fun getItem(p0: Int): Any {
       return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
       return list.size
    }


}