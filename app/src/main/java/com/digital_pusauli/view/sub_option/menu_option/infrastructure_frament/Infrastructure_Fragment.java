package com.digital_pusauli.view.sub_option.menu_option.infrastructure_frament;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.digital_pusauli.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;



public class Infrastructure_Fragment extends Fragment {
    public static Infrastructure_Fragment newInstance() {
        return new Infrastructure_Fragment();
    }

    private Serializable b;
    private ImageView clg_library,clg_cmplab,clg_auditorium,clg_sports,clg_hostel;
    private String library,cmplab,auditorium,sports,hostel;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.infrastructuer_fragment_sub_menu, container, false);
        assert getArguments() != null;
        b= getArguments().getSerializable("INFRASTRUCTURE");


        clg_library=(ImageView)rootView.findViewById(R.id.clg_library);
        clg_cmplab=(ImageView)rootView.findViewById(R.id.clg_cmplab);
        clg_auditorium=(ImageView)rootView.findViewById(R.id.clg_auditorium);
        clg_sports=(ImageView)rootView.findViewById(R.id.clg_sports);
        clg_hostel=(ImageView)rootView.findViewById(R.id.clg_hostel);




        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        library="true";
        cmplab="true";
        auditorium="true";
        sports="false";
        hostel="true";

       /* try {

            JSONArray jsonArr = new JSONArray(b.toString());
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArr.length(); i++)
            {
                jsonObj = jsonArr.getJSONObject(i);
                Log.d("clg_auditorium",jsonArr.toString());

                library=jsonObj.getString("clg_library");
                cmplab=jsonObj.getString("clg_cmplab");
                auditorium=jsonObj.getString("clg_auditorium");
                sports=jsonObj.getString("clg_sports");
                hostel=jsonObj.getString("clg_hostel");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/



        if(library.equals("true")){
            clg_library.setImageResource(R.drawable.ic_checked);
        }
        else {
            clg_library.setImageResource(R.drawable.ic_cancel);
        }



        if(cmplab.equals("true")){
            clg_cmplab.setImageResource(R.drawable.ic_checked);
        }
        else {
            clg_cmplab.setImageResource(R.drawable.ic_cancel);
        }



        if(auditorium.equals("true")){
            clg_auditorium.setImageResource(R.drawable.ic_checked);
        }
        else {
            clg_auditorium.setImageResource(R.drawable.ic_cancel);
        }



       if(sports.equals("true")){
            clg_sports.setImageResource(R.drawable.ic_checked);
        }
        else {
            clg_sports.setImageResource(R.drawable.ic_cancel);
        }


        if(hostel.equals("true")){
            clg_hostel.setImageResource(R.drawable.ic_checked);
        }
        else {
            clg_hostel.setImageResource(R.drawable.ic_cancel);
        }
    }
}