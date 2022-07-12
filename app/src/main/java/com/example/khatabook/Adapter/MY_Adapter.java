package com.example.khatabook.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.service.chooser.ChooserTarget;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatabook.Customer_Detail;
import com.example.khatabook.DataBase.ModelClass;
import com.example.khatabook.MainActivity;
import com.example.khatabook.R;

import java.nio.file.ClosedFileSystemException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

public class MY_Adapter extends RecyclerView.Adapter<MY_Adapter.ViewData> {


    private final Activity activity;
    public  static List<ModelClass> list=new ArrayList<>();
    /*List<ModelClass> slist =new ArrayList<>();*/



    public MY_Adapter(MainActivity mainActivity, List<ModelClass> list) {
        activity=mainActivity;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.record_list,parent,false);
        
        return new ViewData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, @SuppressLint("RecyclerView") int position) {

        holder.ptype.setText(list.get(position).getPtype());
        holder.name.setText((CharSequence) list.get(position).getName());
        holder.payment.setText(list.get(position).getPayment());
        holder.ptype.setText(list.get(position).getPtype());
        String p_type=list.get(position).getPtype();

        Log.e("TAG", "onBindViewHolder: "+list);

        if (p_type.equals(" You Gave")) {
            /*red*/
            holder.payment.setTextColor(Color.parseColor("#E30202"));
            holder.ptype.setTextColor(Color.parseColor("#E30202"));
            holder.logo.setTextColor(Color.parseColor("#E30202"));
        }else{
            /*green*/
            holder.payment.setTextColor(Color.parseColor("#03770C"));
            holder.ptype.setText("You Goted");
            holder.ptype.setTextColor(Color.parseColor("#03770C"));
            holder.logo.setTextColor(Color.parseColor("#03770C"));
        }


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t_id = list.get(position).getId();
                String t_name = list.get(position).getName();
                String t_surname = list.get(position).getSurname();
                String t_email = list.get(position).getEmail();
                String t_address = list.get(position).getAddress();
                String t_mobile = list.get(position).getMobile();
                String t_item=list.get(position).getItem();
                String t_pric= list.get(position).getPayment();
                String t_payment_type1e = list.get(position).getPtype();
                String t_date= list.get(position).getTodaydate();

                ModelClass modelClass  = new ModelClass(t_id,t_name,t_surname,t_email,t_address,t_mobile,t_item,t_pric,t_payment_type1e,t_date);

                // Toast.makeText(activity, "" + t_id, Toast.LENGTH_SHORT).show();


                Intent viewcustomer = new Intent(activity, Customer_Detail.class);

                viewcustomer.putExtra("t_id",t_id);
                viewcustomer.putExtra("t_name",t_name);
                viewcustomer.putExtra("t_surname",t_surname);
                viewcustomer.putExtra("t_email",t_email);
                viewcustomer.putExtra("t_address",t_address);
                viewcustomer.putExtra("t_mobile",t_mobile);
                viewcustomer.putExtra("t_item",t_item);
                viewcustomer.putExtra("t_pric",t_pric);
                viewcustomer.putExtra("t_payment_type1e",t_payment_type1e);
                viewcustomer.putExtra("t_date",t_date);

                activity.startActivityForResult(viewcustomer,2);

            }
        });


    }


    @Override
    public int getItemCount() {

        return list.size();
    }


    class ViewData extends RecyclerView.ViewHolder{

        private final TextView name,payment,logo;
        private final TextView ptype;

        public ViewData(@NonNull View itemView) {
            super(itemView);

            logo=itemView.findViewById(R.id.logo);
            name=itemView.findViewById(R.id.name);
            payment=itemView.findViewById(R.id.payment);
            ptype=itemView.findViewById(R.id.ptype);
        }
    }




}
