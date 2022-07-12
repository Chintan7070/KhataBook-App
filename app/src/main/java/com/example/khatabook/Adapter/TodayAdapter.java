package com.example.khatabook.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatabook.Customer_Detail;
import com.example.khatabook.DataBase.ModelClass;
import com.example.khatabook.MainActivity;
import com.example.khatabook.R;
import com.example.khatabook.Todays;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewData> {


    /*private final Activity activity2;*/
    List<ModelClass> todaylist;
    private final Todays activity;
    static int rqc;


    public TodayAdapter(Todays todays, List<ModelClass> todaylist) {
        activity = todays;
        this.todaylist = todaylist;

    }


    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.todays_recordlist, parent, false);
        return new ViewData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder, @SuppressLint("RecyclerView") int position) {


        /*Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        Log.e("TAG", "onClick: "+formattedDate );
        Toast.makeText(activity,""+formattedDate,Toast.LENGTH_SHORT).show();
*/
        holder.tname.setText(todaylist.get(position).getName());
        holder.tpayment.setText(todaylist.get(position).getPayment());
        String tname = todaylist.get(position).getName();
        String tpayment = todaylist.get(position).getPayment();

        Log.e("TAG", "onBindViewHolder: =========>>>>>>>>>" + tname + "  " + tpayment);

        holder.tname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t_id = todaylist.get(position).getId();
                String t_name = todaylist.get(position).getName();
                String t_surname = todaylist.get(position).getSurname();
                String t_email = todaylist.get(position).getEmail();
                String t_address = todaylist.get(position).getAddress();
                String t_mobile = todaylist.get(position).getMobile();
                String t_item=todaylist.get(position).getItem();
                String t_pric= todaylist.get(position).getPayment();
                String t_payment_type1e = todaylist.get(position).getPtype();
                 String t_date= todaylist.get(position).getTodaydate();

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

                activity.startActivityForResult(viewcustomer, 2);
                //activity.startActivity(viewcustomer);
            }
        });


    }

    @Override
    public int getItemCount() {

        return todaylist.size();
    }


    class ViewData extends RecyclerView.ViewHolder {
        private final TextView tname, tpayment;

        public ViewData(@NonNull View itemView) {
            super(itemView);

            tname = itemView.findViewById(R.id.tname);
            tpayment = itemView.findViewById(R.id.tpayment);


        }
    }
}