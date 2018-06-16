package com.digital_pusauli.view.sub_option.menu_option.faculty;

import android.support.v4.app.Fragment;

public class Faculty_Fragment extends Fragment {
   /* public static Faculty_Fragment newInstance() {
        return new Faculty_Fragment();
    }

    private Serializable b;
    private TextView clg_fac_name,clg_designation,clg_department;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.faculty_fragment_sub_menu, container, false);
        b= getArguments().getSerializable("FACULTY");

        clg_fac_name=(TextView)rootView.findViewById(R.id.clg_fac_name);
        clg_designation=(TextView)rootView.findViewById(R.id.clg_designation);
        clg_department=(TextView)rootView.findViewById(R.id.clg_department);

        return rootView;
    }

    @Override
public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    try {

        JSONArray jsonArr = new JSONArray(b.toString());
        JSONObject jsonObj = null;
        for (int i = 0; i < jsonArr.length(); i++)
        {
            jsonObj = jsonArr.getJSONObject(i);

            clg_fac_name.setText(jsonObj.getString("clg_fac_name"));
            clg_designation.setText(jsonObj.getString("clg_designation"));
            clg_department.setText(jsonObj.getString("clg_department"));

        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
}*/
}