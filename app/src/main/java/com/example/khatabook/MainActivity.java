package com.example.khatabook;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.khatabook.Adapter.MY_Adapter;
import com.example.khatabook.DataBase.DB_Helper;
import com.example.khatabook.DataBase.ModelClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.StatementEvent;

public class MainActivity extends AppCompatActivity {

    private LinearLayout addcustomer;
    private ImageView today;
    TextView goted,get;
    private RecyclerView rc_view;

    public  static List<ModelClass> list=new ArrayList<>();
    /*List<ModelClass> list = new ArrayList<>();*/
    private TextView view_report;
    private TextView create_khatabook;
    DB_Helper db_helper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addcustomer=findViewById(R.id.addcustomer);
        today=findViewById(R.id.today);
        get=findViewById(R.id.get);
        goted=findViewById(R.id.got);
        create_khatabook=findViewById(R.id.create);
        view_report=findViewById(R.id.view_report);
        rc_view=findViewById(R.id.rc_view);


        forSharedPreferences();
        addcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addcusto=new Intent(MainActivity.this,AddCustomer.class);
                startActivityForResult(addcusto, 2);

            }
        });
        


        db_helper=new DB_Helper(MainActivity.this);
        list=db_helper.readData();
        setAdapter();

        int totalgave=0 ,totalgoted=0;

        for (int i=0 ; i<list.size() ; i++) {

            String payment1 = (list.get(i).getPayment());
            String ptype = list.get(i).getPtype();

            if (ptype.equals(" You Gave")){
            totalgave= totalgave + Integer.parseInt(payment1);

            }else{
                String payment = (list.get(i).getPayment());
                totalgoted = totalgoted + Integer.parseInt(payment);

            }
        }

        goted.setText(""+totalgoted);
        get.setText(""+totalgave);

        /*Toast.makeText(MainActivity.this,"got:"+totalgoted,Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this,"gave:"+totalgave,Toast.LENGTH_SHORT).show();*/

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent today=new Intent(MainActivity.this,Todays.class);
                startActivityForResult(today,2);
            }
        });

        view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*  for find todays date
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault());
                String formattedDate = df.format(c);

                Log.e("TAG", "onClick: "+formattedDate );*/
                Toast.makeText(MainActivity.this,"Not Created",Toast.LENGTH_SHORT).show();
            }
        });

        create_khatabook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBottomsheetdialog();
            }
        });
    }


    private void setBottomsheetdialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_demo);
        bottomSheetDialog.getBehavior();
        bottomSheetDialog.show();

        LinearLayout removebg = findViewById(R.id.removebg);
        EditText comname = bottomSheetDialog.findViewById(R.id.btomname);
        TextView btomdone = bottomSheetDialog.findViewById(R.id.btomdone);




        btomdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compname = comname.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", compname);
                myEdit.commit();
                create_khatabook.setText(compname);

                bottomSheetDialog.dismiss();
            }
        });
    }//dialog finish


    private void forSharedPreferences() {
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        if (s1.equals("")){
        create_khatabook.setText("Create Khatabook");
        }else{
            create_khatabook.setText(s1);

        }
    }


    private void setAdapter(){

        MY_Adapter my_adapter=new MY_Adapter(MainActivity.this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rc_view.setAdapter(my_adapter);
        rc_view.setLayoutManager(layoutManager);


    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            db_helper=new DB_Helper(MainActivity.this);
            list=db_helper.readData();
            setAdapter();

        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==2)
        {
            db_helper=new DB_Helper(MainActivity.this);
            list=db_helper.readData();
            setAdapter();

            int totalgave = 0, totalgoted = 0;
            goted.setText("0");
            get.setText("0");
            for (int i=0 ; i<list.size() ; i++) {

                int payment11= Integer.parseInt(list.get(i).getPayment());
                String ptype = list.get(i).getPtype();
                
                if (ptype.equals(" You Gave")){
                    totalgave= totalgave + payment11;

                }else{
                    totalgoted = totalgoted + payment11;
                }
                goted.setText(""+totalgoted);
                get.setText(""+totalgave);
            }

        }
    }



}