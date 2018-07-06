package com.digital_pusauli.view.sub_option.menu_option.about

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.digital_pusauli.R
import com.digital_pusauli.utils.Utils.toast

import java.io.Serializable

class About_Fragment : Fragment() {
    private var b: Serializable? = null
    private var clg_about_1: TextView? = null
    private var clg_about_2: TextView? = null
    private var clg_about_3: TextView? = null
    private var clg_address: TextView? = null
    private var clg_number: TextView? = null
    private var clg_website: TextView? = null

    //private lateinit var context: Context

    companion object {
        fun newInstance(): About_Fragment {
            return About_Fragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.about_fragment_sub_menu, container, false)
        assert(arguments != null)
        b = arguments!!.getSerializable("ABOUT")


                activity!!.toast("Hello world!")

        clg_about_1 = rootView.findViewById<View>(R.id.clg_about_1) as TextView
        clg_about_2 = rootView.findViewById<View>(R.id.clg_about_2) as TextView
        clg_about_3 = rootView.findViewById<View>(R.id.clg_about_3) as TextView
        clg_address = rootView.findViewById<View>(R.id.clg_address) as TextView
        clg_number = rootView.findViewById<View>(R.id.clg_number) as TextView
        clg_website = rootView.findViewById<View>(R.id.clg_website) as TextView

        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        clg_about_1!!.setOnClickListener { activity!!.toast("clg_about_1")
        }

      /*  clg_about_1!!.text = "clg_about_1"
        clg_about_2!!.text = "clg_about_2"
        clg_about_3!!.text = "clg_about_3"
        clg_address!!.text = "clg_address"
        clg_number!!.text = "clg_number"
        clg_website!!.text = "clg_website"
*/
        /*  try {

            JSONArray jsonArr = new JSONArray(b.toString());
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArr.length(); i++)
            {
                jsonObj = jsonArr.getJSONObject(i);

                clg_about_1.setText(jsonObj.getString("clg_about_1"));
                clg_about_2.setText(jsonObj.getString("clg_about_2"));
                clg_about_3.setText(jsonObj.getString("clg_about_3"));
                clg_address.setText(jsonObj.getString("clg_address"));
                clg_number.setText(jsonObj.getString("clg_number"));
                clg_website.setText(jsonObj.getString("clg_website"));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
/*
    private fun log(mess:String){
        Utils.log(TAG,mess)
    }
    private fun toast(mess:String){
        Utils.toast(context,mess)
    }*/



}