/*
package com.digital_pusauli.view.categroy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private TextView tv_country1;
    private TextView tv_country2;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_row);


        img = (ImageView) findViewById(R.id.img);
        tv_country1 = (TextView) findViewById(R.id.tv_country1);
        tv_country2 = (TextView) findViewById(R.id.tv_country2);


        Movie movie = getIntent().getParcelableExtra("Key");


        img.setImageResource(movie.getImage());
        tv_country1.setText(""+movie.getTitle());
        tv_country2.setText(""+movie.getYear());

    }
}
*/
