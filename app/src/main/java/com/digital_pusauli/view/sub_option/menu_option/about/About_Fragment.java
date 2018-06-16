package com.digital_pusauli.view.sub_option.menu_option.about;

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

public class About_Fragment extends Fragment {

    public static About_Fragment newInstance() {
        return new About_Fragment();
    }
    private Serializable b;
    private TextView clg_about_1,clg_about_2,clg_about_3,clg_address,clg_number,clg_website;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment_sub_menu, container, false);
        assert getArguments() != null;
        b= getArguments().getSerializable("ABOUT");



        clg_about_1=(TextView)rootView.findViewById(R.id.clg_about_1);
        clg_about_2=(TextView)rootView.findViewById(R.id.clg_about_2);
        clg_about_3=(TextView)rootView.findViewById(R.id.clg_about_3);
        clg_address=(TextView)rootView.findViewById(R.id.clg_address);
        clg_number=(TextView)rootView.findViewById(R.id.clg_number);
        clg_website=(TextView)rootView.findViewById(R.id.clg_website);

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        clg_about_1.setText("clg_about_1");
        clg_about_2.setText("clg_about_2");
        clg_about_3.setText("clg_about_3");
        clg_address.setText("clg_address");
        clg_number.setText("clg_number");
        clg_website.setText("clg_website");

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

}