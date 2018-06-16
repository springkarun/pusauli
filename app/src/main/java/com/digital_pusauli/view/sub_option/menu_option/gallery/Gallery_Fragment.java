package com.digital_pusauli.view.sub_option.menu_option.gallery;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.digital_pusauli.R;
import com.github.florent37.materialleanback.MaterialLeanBack;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Gallery_Fragment extends Fragment {
    public static Gallery_Fragment newInstance() {
        return new Gallery_Fragment();
    }

    private ArrayList<Model_Gallerys> list;
    private ArrayList<Model_Video> video;
    MaterialLeanBack materialLeanBack;
    HashMap<String, String> mDataMap = null;

    private Serializable b;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery_fragment_sub_menu, container, false);
        assert getArguments() != null;
        b= getArguments().getSerializable("GALLERY");
        //Log.d("GALLERY",b.toString());

        materialLeanBack = rootView.findViewById(R.id.materialLeanBack1);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        video = new ArrayList<>();
        list.clear();
        video.clear();
        setupView();


          list.add(new Model_Gallerys("https://randomwire.com/wp-content/uploads/hua-cheng-bei-electronics.jpeg"));
          list.add(new Model_Gallerys("http://tastegrowersmarket.com.au/wp-content/uploads/2016/03/produce.jpg"));
          list.add(new Model_Gallerys("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
          list.add(new Model_Gallerys("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
          list.add(new Model_Gallerys("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
          list.add(new Model_Gallerys("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
          list.add(new Model_Gallerys("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));



        video.add(new Model_Video("https://images.pexels.com/photos/56866/garden-rose-red-pink-56866.jpeg?cs=srgb&dl=dewdrops-flora-flower-56866.jpg&fm=jpg"));
        video.add(new Model_Video("http://tastegrowersmarket.com.au/wp-content/uploads/2016/03/produce.jpg"));
        video.add(new Model_Video("https://images.fineartamerica.com/images-medium-large-5/amber-flush-rose-hanza-turgul.jpg"));
        video.add(new Model_Video("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
        video.add(new Model_Video("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
        video.add(new Model_Video("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));
        video.add(new Model_Video("http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg"));


    }

    private void setupView() {

        materialLeanBack.setCustomizer(textView -> {
            textView.setTypeface(null, Typeface.BOLD);
          //  textView.setVisibility(View.GONE);
        });

        materialLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
            @Override
            public int getLineCount() {
                return 2;
            }

            @Override
            public int getCellsCount(int line) {
                return 4;
            }

            @Override
            public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);
                return new TestViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TestViewHolder viewHolder, int i) {


                    Model_Gallerys postion = list.get(i);
                    viewHolder.textView.setVisibility(View.GONE);
                    Picasso.with(getActivity()).load(postion.getClg_image_1())
                            .placeholder(R.mipmap.ic_launcher)
                            .into(viewHolder.imageView);

                   Model_Video v = video.get(i);
                    viewHolder.textView.setVisibility(View.GONE);
                    Picasso.with(getActivity()).load(v.getVideo_url())
                            .placeholder(R.mipmap.ic_launcher)
                            .into(viewHolder.imageView);


            }

            @Override
            public String getTitleForRow(int row) {

                if(row==0) {
                    return "Gallery";
                }else
                    return "Videos";

            }

            @Override
            public boolean hasRowTitle(int row) {
                return row != 6;
            }


            @Override
            public boolean isCustomView(int row) {
                return row == 3;
            }

            @Override
            public void onBindCustomView(RecyclerView.ViewHolder viewHolder, int row) {
                super.onBindCustomView(viewHolder, row);
            }

            //endregion

        });

        materialLeanBack.setOnItemClickListener(new MaterialLeanBack.OnItemClickListener() {
            @Override
            public void onTitleClicked(int row, String text) {
                Toast.makeText(getActivity(), "onTitleClicked " + row + " " + text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClicked(int row, int column) {

              /*  if (row == 0 && column == 1) {
                    //Intent intent=new Intent(getActivity(),Sub_Class_Medical_info.class);
                   // startActivity(intent);
                } else if (row == 0 && column == 2) {
                }else if (row == 0 && column == 3) {
                }else if (row == 0 && column == 4) {
                }else if (row == 1 && column == 1) {
                }else if (row == 1 && column == 2) {
                }else if (row == 1 && column == 3) {
                }else if (row == 1 && column == 4) {
                }*/

            }
        });
    }

  /*  private void initJson() {

      String url="https://raw.githubusercontent.com/springkarun/pusauli_data/master/.gitignore/gallery.json";
        Ion.with(this)
                .load("GET",url)
                .asString().setCallback((error, data) -> {
                    try {
                        if (data != null) {
                            HashMap<String, String> map0 = new HashMap<>();
                            JSONObject response = new JSONObject(data);

                            JSONObject object0=response.getJSONObject("course_data");

                            JSONObject course_data0=object0.getJSONObject("course_1");
                            String course_name0=course_data0.getString("course_name");
                            String course_image0=course_data0.getString("course_image");


                            JSONObject course_data1=object0.getJSONObject("course_2");
                            String course_name1=course_data1.getString("course_name");
                            String course_image1=course_data1.getString("course_image");


                            JSONObject course_data2=object0.getJSONObject("course_3");
                            String course_name2=course_data2.getString("course_name");
                            String course_image2=course_data2.getString("course_image");



                            JSONObject course_data3=object0.getJSONObject("course_4");
                            String course_name3=course_data3.getString("course_name");
                            String course_image3=course_data3.getString("course_image");

                            map0.put("course_name0",course_name0);
                            map0.put("course_image0",course_image0);

                            map0.put("course_name1",course_name1);
                            map0.put("course_image1",course_image1);

                            map0.put("course_name2",course_name2);
                            map0.put("course_image2",course_image2);

                            map0.put("course_name3",course_name3);
                            map0.put("course_image3",course_image3);

                            JSONObject object1=response.getJSONObject("college_data");

                            JSONObject course_data11=object1.getJSONObject("college_1");
                            String course_name11=course_data11.getString("college_name");
                            String course_image11=course_data11.getString("college_image");


                            JSONObject course_data22=object1.getJSONObject("college_2");
                            String course_name22=course_data22.getString("college_name");
                            String course_image22=course_data22.getString("college_image");


                            JSONObject course_data33=object1.getJSONObject("college_3");
                            String course_name33=course_data33.getString("college_name");
                            String course_image33=course_data33.getString("college_image");



                            JSONObject course_data44=object1.getJSONObject("college_4");
                            String course_name44=course_data44.getString("college_name");
                            String course_image44=course_data44.getString("college_image");

                            map0.put("course_name11",course_name11);
                            map0.put("course_image11",course_image11);

                            map0.put("course_name22",course_name22);
                            map0.put("course_image22",course_image22);

                            map0.put("course_name33",course_name33);
                            map0.put("course_image33",course_image33);

                            map0.put("course_name44",course_name44);
                            map0.put("course_image44",course_image44);





                            Log.d("Course0: ",course_name11 +"   :   "+course_image11);
                            Log.d("Course1: ",course_name1 +"   :   "+course_image1);
                            Log.d("Course2: ",course_name2 +"   :   "+course_image2);
                            Log.d("Course3: ",course_name3 +"   :   "+course_image3);

                            if (map0 != null) {
                                setHashData(map0);
                                setupView();
                            } else {
                                Log.d("TESTMOHIT", "Your DataList is Not Embedded in Map");
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
    }*/

    private HashMap getHashData() {
        return this.mDataMap;
    }

    private void setHashData(HashMap<String, String> mData) {
        this.mDataMap = mData;
    }


}