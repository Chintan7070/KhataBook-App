package com.example.khatabook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khatabook.DataBase.DB_Helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddCustomer extends AppCompatActivity {

    private LinearLayout date;
    private EditText price,name,surname,email,address,mobile,item;
    private RadioGroup rg;
    public static TextView txtdate;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        name=findViewById(R.id.namee);
        surname=findViewById(R.id.surname);
        email=findViewById(R.id.emaill);
        address=findViewById(R.id.address);
        mobile=findViewById(R.id.mobile);
        item=findViewById(R.id.item);
        price=findViewById(R.id.price);
        date=findViewById(R.id.date);
        txtdate=findViewById(R.id.txtdate);
        rg = findViewById(R.id.radioGroup);
        add=findViewById(R.id.add);




        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog();
            }
        });


        DB_Helper db_helper=new DB_Helper(AddCustomer.this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String payment_type = "";
                payment_type = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

                Log.e("TAG", "onClick: +"+payment_type );

                String payment_type1= null;
                String name1=name.getText().toString();
                String surname1=surname.getText().toString();
                String email1=email.getText().toString();
                String address1=address.getText().toString();
                String mobile1=mobile.getText().toString();
                String item1=item.getText().toString();
                String price1=price.getText().toString();
                payment_type1=payment_type;
                String date1=txtdate.getText().toString();


                if (price1.isEmpty() ||  date1.isEmpty() || payment_type1.equals(""))
                {

                    Toast.makeText(AddCustomer.this,"Enter Price,payment type and date is compulsory",Toast.LENGTH_SHORT).show();

                }else{
                db_helper.insertData(name1,surname1,email1,address1,mobile1,item1,price1,payment_type1,date1);
                }

            }

        });


        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String payment_type;

                payment_type = ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();

                Log.e("TAG", "onClick: +"+payment_type );

                String name1=name.getText().toString();
                String surname1=surname.getText().toString();
                String email1=email.getText().toString();
                String address1=address.getText().toString();
                String mobile1=mobile.getText().toString();
                String item1=item.getText().toString();
                String price1=price.getText().toString();
                String payment_type1=payment_type;
                String date1=txtdate.getText().toString();

                db_helper.insertData(name1,surname1,email1,address1,mobile1,item1,price1,payment_type1,date1);
            }
        });*/

    }

    void DatePickerDialog(){

        Calendar calendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(AddCustomer.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

           datePickerDialog.show();
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent=new Intent();
        intent.putExtra("","");
        setResult(2,intent);
        return super.onKeyDown(keyCode, event);
    }

}