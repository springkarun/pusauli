package com.digital_pusauli.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.digital_pusauli.R;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    FlipperLayout flipperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);

        setLayout();
    }

    private void setLayout() {
        String url[] = new String[]{
                "http://images.indianexpress.com/2017/12/national-anthem-lead.jpg",
                "https://studentcareercoach.files.wordpress.com/2012/06/bigstock_portrait_of_happy_teens_lookin_13855142.jpg",
                "https://static.socialpost.news/wp-content/uploads/2018/02/students.jpg",
                "http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg",
                "https://i.ytimg.com/vi/2NM5Vdtoy1o/maxresdefault.jpg",
                "http://images.newindianexpress.com/uploads/user/imagelibrary/2017/4/18/original/Where4.jpg"
        };
        for (int i = 0; i < 6; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl(url[i]);
                   // .setDescription("Cool" + (i + 1));
            flipperLayout.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(MainActivity.this
                            , "Here " + (flipperLayout.getCurrentPagePosition() + 1)
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}