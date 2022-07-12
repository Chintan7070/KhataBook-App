package com.example.khatabook;

import static com.example.khatabook.Adapter.MY_Adapter.list;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khatabook.DataBase.DB_Helper;
import com.example.khatabook.DataBase.ModelClass;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import javax.sql.StatementEvent;

public class Customer_Detail extends AppCompatActivity {

    private EditText cname,csurname,cemail,caddress,cmobile,citem,cprice;
    LinearLayout cdate,backcd;
    TextView ctxtdate,t_name,t_surname;
    RadioGroup rgcptype;

    String tid,tName,tSurname,temail,taddress,tmobile,titem,tpric,tdate,tpayment_type1e;

  private Button delete,update;
    private LinearLayout sms,call,whatsapp;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        t_name=findViewById(R.id.t_name);
        t_surname=findViewById(R.id.t_surname);

        backcd=findViewById(R.id.backcd);

        cname=findViewById(R.id.cname);
        csurname=findViewById(R.id.csurname);
        cemail=findViewById(R.id.cemail);
        caddress=findViewById(R.id.caddress);
        cmobile=findViewById(R.id.cmobile);
        citem=findViewById(R.id.citem);
        cprice=findViewById(R.id.cprice);
        rgcptype=findViewById(R.id.c_radioGroup);
        cdate=findViewById(R.id.c_date);
        ctxtdate=findViewById(R.id.ctxtdate);

        update=findViewById(R.id.btn_upadte);
        delete=findViewById(R.id.btn_delete);

        whatsapp=findViewById(R.id.whatsap);
        call=findViewById(R.id.call);
        sms=findViewById(R.id.sms);



        int position=getIntent().getIntExtra("position",0);
        tid = getIntent().getStringExtra("t_id");
        tName = getIntent().getStringExtra("t_name");
        tSurname = getIntent().getStringExtra("t_surname");
        temail = getIntent().getStringExtra("t_email");
        taddress = getIntent().getStringExtra("t_address");
        tmobile = getIntent().getStringExtra("t_mobile");
        titem = getIntent().getStringExtra("t_item");
        tpric = getIntent().getStringExtra("t_pric");
        tpayment_type1e = getIntent().getStringExtra("t_payment_type1e");
        tdate = getIntent().getStringExtra("t_date");


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=list.get(position).getMobile();
                if (mobile.isEmpty()) {

                    Toast.makeText(Customer_Detail.this,"Contact not found",Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(Customer_Detail.this,""+mobile,Toast.LENGTH_SHORT).show();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+mobile));
                    startActivity(callIntent);
                }
            }
        });


        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=list.get(position).getMobile();
                String user=list.get(position).getName();
                String item=list.get(position).getItem();
                String payment=list.get(position).getPayment();
                if (mobile.isEmpty()){

                        Toast.makeText(Customer_Detail.this,"Contact not found",Toast.LENGTH_LONG).show();

                }else{
                    String sms="hii "+user+", We have not received payment for your "+item+".\n Please pay "+payment+" today. \n -Khatabook";
                    /*SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mobile, null, sms, null, null);*/
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + mobile));
                    intent.putExtra( "sms_body","hii "+user+", We have not received payment for your "+item+".\nPlease pay "+payment+" today. \n:-Khatabook");
                    startActivity(intent);
                }
            }
        });


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WhatsApp();
            }

            private void WhatsApp() {

                String mobile = list.get(position).getMobile();
                String user = list.get(position).getName();
                String item = list.get(position).getItem();
                String payment = list.get(position).getPayment();
                String sms = "hii " + user + ", We have not received payment for your " + item + ".\n Please pay " + payment + " today. \n -Khatabook";

                // Method ==================== 111111
                    Uri uri = Uri.parse("smsto:" + mobile);
                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                    //i.putExtra(Intent.EXTRA_TEXT, sms);
                    i.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    i.putExtra(Intent.EXTRA_PACKAGE_NAME,sms);
                    i.putExtra("sms_body", "hii " + user + ", We have not received payment for your " + item + ".\n Please pay " + payment + " today. \n -Khatabook");
                    i.setPackage("com.whatsapp");
                     i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(i);



                // Method ==================== 222222
                // not Work

                /*PackageManager pm=getPackageManager();
                try {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "This is  a Test"; // Replace with your own message.

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    waIntent.setPackage(String.valueOf(info));
                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(Customer_Detail.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();

                }catch(Exception e){

                }*/

            }
        });


        cdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerdialog();
            }

        });


        t_name.setText(tName);
        t_surname.setText(tSurname);

        cname.setText(tName);
        csurname.setText(tSurname);
        cemail.setText(temail);
        caddress.setText(taddress);
        cmobile.setText(tmobile);
        citem.setText(titem);
        cprice.setText(tpric);
        if (tpayment_type1e.equals(" You Gave"))
        {
            rgcptype.check(R.id.gave);
        }
        else{
            rgcptype.check(R.id.got);
        }
        ctxtdate.setText(tdate);


        DB_Helper db_helper = new DB_Helper(Customer_Detail.this);

        backcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","one");
                setResult(2,intent);
                finish();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String payment_type;
                payment_type = ((RadioButton)findViewById(rgcptype.getCheckedRadioButtonId()))
                        .getText().toString();
                Log.e("TAG", "onClick: "+payment_type );

                //String c_id=list.get(position).getId();
                /*Toast.makeText(Customer_Detail.this,"id is"+c_id,Toast.LENGTH_LONG).show();*/
                String c_name=cname.getText().toString();
                String c_surname=csurname.getText().toString();
                String c_email=cemail.getText().toString();
                String c_address=caddress.getText().toString();
                String c_mobile=cmobile.getText().toString();
                String c_item=citem.getText().toString();
                String c_price=cprice.getText().toString();
                String payment_type1=payment_type;
                String c_date=ctxtdate.getText().toString();

                Log.e("TAG", "onClick: "+tid+"  "+c_name+"  "+c_surname+"  "+c_email+"  "+c_address+"  "+c_mobile+"  "+c_item+"  "+c_price+"  "+payment_type1+"  "+c_date );
                Toast.makeText(Customer_Detail.this,"Successfully Updated",Toast.LENGTH_LONG).show();
                db_helper.updateData(tid,c_name,c_surname,c_email,c_address,c_mobile,c_item,c_price,payment_type1,c_date);


            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String c_id=list.get(position).getId();
                /*Toast.makeText(Customer_Detail.this,""+c_id,Toast.LENGTH_SHORT).show();*/
                db_helper.deleteData(tid);
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","one");
                setResult(2,intent);
                Toast.makeText(Customer_Detail.this,"Record Deleted"+position,Toast.LENGTH_LONG).show();
                finish();


            }
        });

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Intent intent=new Intent();
        intent.putExtra("","");
        setResult(2,intent);
        return super.onKeyUp(keyCode, event);
    }


    private void datepickerdialog() {
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(Customer_Detail.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                /*txtdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);*/
                ctxtdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                /*Toast.makeText(Customer_Detail.this,"date is"+ctxtdate,Toast.LENGTH_SHORT).show();*/
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }





}