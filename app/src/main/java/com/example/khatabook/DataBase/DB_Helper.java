package com.example.khatabook.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {
    public static String DB_Name = "CustData.db";
    private final Context context;
    private ModelClass modelData;

    public DB_Helper(@Nullable Context context) {
        super(context, DB_Name, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //String query = "CREATE TABLE customer (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT,email TEXT,addredd TEXT,mobile TEXT,iten TEXT,price TEXT,p_type TEXT,date TEXT)";
        String query="CREATE TABLE customer(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, surname TEXT, email TEXT, address TEXT, mobile TEXT, item TEXT, price TEXT,p_type TEXT, date TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String name1, String surname1, String email1, String address1, String mobile1, String item1, String price1,String p_type1,String date1) {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues =new ContentValues();

        contentValues.put("name",name1);
        contentValues.put("surname",surname1);
        contentValues.put("email",email1);
        contentValues.put("address",address1);
        contentValues.put("mobile",mobile1);
        contentValues.put("item",item1);
        contentValues.put("price",price1);
        contentValues.put("p_type",p_type1);
        contentValues.put("date",date1);

        long tos=db.insert("customer",null,contentValues);

        Log.e("TAG", "insertData is+++++++: "+ name1+"   "+surname1+"   "+email1+"   "+address1+"   "+mobile1+"   "+item1+"   "+price1+"   "+p_type1+"   "+date1);
        Toast.makeText(context,"Successfully Inserted",Toast.LENGTH_SHORT).show();

    }

    public List<ModelClass> readData() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<ModelClass> list = new ArrayList<>();

        String query = "SELECT * FROM customer";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String surname=cursor.getString(cursor.getColumnIndex("surname"));
                @SuppressLint("Range") String email=cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String address=cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String mobile= cursor.getString(cursor.getColumnIndex("mobile"));
                @SuppressLint("Range") String item=cursor.getString(cursor.getColumnIndex("item"));
                @SuppressLint("Range") String payment = cursor.getString(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String ptype = cursor.getString(cursor.getColumnIndex("p_type"));
                @SuppressLint("Range") String todaydate = cursor.getString(cursor.getColumnIndex("date"));

                ModelClass modelclass = new ModelClass(id,name,surname,email,address,mobile,item,payment,ptype,todaydate);

                list.add(modelclass);

            } while (cursor.moveToNext());

        }
        return list;
    }



    public void updateData(String c_id, String c_name, String c_surname, String c_email, String c_address, String c_mobile, String c_item, String c_price, String c_Ptype, String c_date){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",c_id);
        contentValues.put("name",c_name);
        contentValues.put("surname",c_surname);
        contentValues.put("email",c_email);
        contentValues.put("address",c_address);
        contentValues.put("mobile",c_mobile);
        contentValues.put("item",c_item);
        contentValues.put("price",c_price);
        contentValues.put("p_type",c_Ptype);
        contentValues.put("date",c_date);

        Log.e("TAG", "update Successfully: +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+c_name+"|"+c_surname+"|"+c_email+"|"+c_address+"|"+c_mobile+"|"+c_item+"|"+c_price+"|"+c_Ptype+"|"+c_date );
        sqLiteDatabase.update("customer",contentValues,"id=?",new String[]{c_id});

    }

    public void deleteData(String id){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        sqLiteDatabase.delete("customer","id=?",new String[]{id});
    }


    public void readDataId(int position) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        List<ModelClass> list = new ArrayList<>();

        String query = "SELECT * FROM customer WHERE id = "+position;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String surname=cursor.getString(cursor.getColumnIndex("surname"));
                @SuppressLint("Range") String email=cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String address=cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String mobile= cursor.getString(cursor.getColumnIndex("mobile"));
                @SuppressLint("Range") String item=cursor.getString(cursor.getColumnIndex("item"));
                @SuppressLint("Range") String payment = cursor.getString(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String ptype = cursor.getString(cursor.getColumnIndex("p_type"));
                @SuppressLint("Range") String todaydate = cursor.getString(cursor.getColumnIndex("date"));

                ModelClass modelclass = new ModelClass(id,name,surname,email,address,mobile,item,payment,ptype,todaydate);

                list.add(modelclass);

            } while (cursor.moveToNext());

        }

    }
}
