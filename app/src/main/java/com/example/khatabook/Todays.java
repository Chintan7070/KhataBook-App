package com.example.khatabook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatabook.Adapter.TodayAdapter;
import com.example.khatabook.DataBase.DB_Helper;
import com.example.khatabook.DataBase.ModelClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Todays extends AppCompatActivity {

    private LinearLayout back;
    private RecyclerView rc_today;
    private List<ModelClass> list;
    private List<ModelClass> todaylist = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays);


        rc_today = findViewById(R.id.rc_today);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list=new ArrayList<>();
        list.clear();

        DB_Helper db_helper = new DB_Helper(Todays.this);
        list=new ArrayList<>();
        list.clear();
        list=new ArrayList<>();

        list = db_helper.readData();
        Toast.makeText(Todays.this,"Todays List",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onCreate: "+list );




        /*Toast.makeText(Todays.this,""+today,Toast.LENGTH_SHORT).show();*/


        for (int i = 0; i < list.size(); i++) {

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());
            String today = df.format(c);
            Log.e("TAG", "today: " +today);

            String alldays = list.get(i).getTodaydate();
            //Log.e("TAG", "allday"+alldays );
            String ptype = list.get(i).getPtype();
            String name= list.get(i).getName();
            /* String payment=list.get(i).getPayment();*/

            if (alldays.equals(today) && ptype.equals(" You Gave")) {

                todaylist.add(list.get(i));
                Log.e("TAG", "onCreate:  "+todaylist);
                Log.e("TAG", "********   TRUE   *********    "+name+" **  "+alldays+"  **"+ptype+"  **  "+today);
            }else{
                Log.e("TAG", "  **  false  **");
            }
        }
        recycler();
    }


    void recycler(){

        TodayAdapter todayAdapter = new TodayAdapter(Todays.this, todaylist);
        rc_today.setAdapter(todayAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rc_today.setLayoutManager(layoutManager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());
            String today = df.format(c);
            todaylist=new ArrayList<>();
            DB_Helper db_helper=new DB_Helper(Todays.this);

            list.clear();
            list = db_helper.readData();

            todaylist.clear();

            for (int i = 0; i < list.size(); i++) {

                 String alldays = list.get(i).getTodaydate();
                 String ptype = list.get(i).getPtype();
                 String payment=list.get(i).getPayment();

                Log.e("TAG", "onActivityResult: ===========>>>>>>>>>>>"+alldays+"  **  "+ptype+"  **  "+today );

                if (alldays.equals(today) && ptype.equals(" You Gave")) {
                    Log.e("TAG", "onActivityResult:DDDDDDDDDDDDDDDDDDDDDDDDD PTYPE"+ ptype+"  ALLDATE  DDDDDDDDDDDDDDD"+alldays);

                    todaylist.add(list.get(i));

                }else{
                    Log.e("TAG", "///////////////////////   COINDITION FALSE   //////////////////" );
                }
                TodayAdapter todayAdapter = new TodayAdapter(Todays.this, todaylist);
                rc_today.setAdapter(todayAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                rc_today.setLayoutManager(layoutManager);
            }

        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent=new Intent();
        intent.putExtra("","");
        setResult(2,intent);
        return super.onKeyDown(keyCode, event);
    }
}