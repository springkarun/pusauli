package com.digital_pusauli.view.sub_option.menu_option.couress;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digital_pusauli.R;

import java.io.Serializable;

public class Courses_Fragment extends Fragment {
    public static Courses_Fragment newInstance() {
        return new Courses_Fragment();
    }
    private Serializable b;
    private TextView clg_course_name,clg_course_fees,clg_course_entrance,clg_course_type,clg_course_duration;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.courses_fragment_sub_menu, container, false);
        assert getArguments() != null;
        b= getArguments().getSerializable("COURSES");

        clg_course_name=(TextView)rootView.findViewById(R.id.clg_course_name);
        clg_course_fees=(TextView)rootView.findViewById(R.id.clg_course_fees);
        clg_course_entrance=(TextView)rootView.findViewById(R.id.clg_course_entrance);
        clg_course_type=(TextView)rootView.findViewById(R.id.clg_course_type);
        clg_course_duration=(TextView)rootView.findViewById(R.id.clg_course_duration);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        clg_course_name.setText("clg_course_name");
        clg_course_fees.setText("₹ "+"clg_course_fees");
        clg_course_entrance.setText("clg_course_entrance");
        clg_course_type.setText("clg_course_type");
        clg_course_duration.setText("clg_course_duration");

     /*   try {

            JSONArray jsonArr = new JSONArray(b.toString());
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArr.length(); i++)
                 {
                    jsonObj = jsonArr.getJSONObject(i);

                     clg_course_name.setText(jsonObj.getString("clg_course_name"));
                     clg_course_fees.setText("₹ "+jsonObj.getString("clg_course_fees"));
                     clg_course_entrance.setText(jsonObj.getString("clg_course_entrance"));
                     clg_course_type.setText(jsonObj.getString("clg_course_type"));
                     clg_course_duration.setText(jsonObj.getString("clg_course_duration"));


                Log.d("COURSES_TESTwwwww: ",jsonObj.toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }


}