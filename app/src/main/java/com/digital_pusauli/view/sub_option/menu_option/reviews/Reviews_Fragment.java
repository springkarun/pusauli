package com.digital_pusauli.view.sub_option.menu_option.reviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital_pusauli.R;


public class Reviews_Fragment extends Fragment {
    public static Reviews_Fragment newInstance() {
        return new Reviews_Fragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reviews_fragment_sub_menu, container, false);

      /*  Bundle b = getArguments();
        if (b != null) {
            Log.d("EXTRAS",b.getString("REVIEWS"));
        } else {
            Log.e("FAIL", "...");
        }
*/
        return rootView;
    }
}