package com.example.recyclertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Product_info.db";

    // database table name
    public static final String TABLE_NAME = "Product_list";
    public static final String TABLE_NAME_SEARTCH = "Product_Search";
    public static final String TABLE_NAME_STOCK = "Product_Stock";
    public static final String TABLE_NAME_PURCHESS = "Purchess_table";
    public static final String TABLE_UNPAID_AND_PAID = "Paid_and_unpaid";
    public static final String TABLE_STOCT_OUT_LIST = "Stoct_out_list";


    //Product info database
    public static final String COLUMN_ID = "ID";
    public static final String BRAND_NAME = "brand_name";
    public static final String PRODUCT_NAME = "product_name";
    public static final String GRANARIC = "generic";
    public static final String STRENGTH = "strength";
    public static final String TABLE_EG = "form_eg_table";
    public static final String PRODUCT_ID = "product_id";
    public static final String CATALOGS = "catalogs";


    // Stock table
    public static final String TATOL_STOCK = "tatol_stock";
    public static final String STOCK_RATS = "stock_rate";
    public static final String USER_ID = "user_id";
    public static final String DATE = "date";


    // purchess table database
    public static final String PRIDUCT_ID = "product_id";
    public static final String PHARMACY_ID = "pharmacy_id";
    public static final String NET_VALUE = "net_value";
    public static final String USER_ID_FOR_PURCHESS = "user_id";
    public static final String PIECES = "pieces";
    public static final String TOTAL_VALUE = "total_value";
    public static final String INDENTY_VALUE = "indenty_value";


    //paid and unpaid database
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String PURCHASS = "purchass_id";
    public static final String TOTAL_TK = "total_tk";
    public static final String PAID_AMOUT = "paid_amount";
    public static final String DEBTS = "debts_tk";


    //paid and unpaid database
    public static final String PHARMARCY_ID = "pharmacy_id";
    public static final String BRAND_ID = "brand_id";
    public static final String PRODUET_NAME = "product_id";
    public static final String GANARIC_NAME = "ganaric_name";
    public static final String STROGEST_CAPASITY = "strogest_capasity";
    public static final String TABLE_STOCT_LIST = "table_s";



    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BRAND_NAME + " VARCHAR, "
                + PRODUCT_NAME + " VARCHAR, "
                + GRANARIC + " VARCHAR, "
                + STRENGTH + " VARCHAR, "
                + TABLE_EG + " VARCHAR, "
                + PRODUCT_ID + " VARCHAR, "
                + CATALOGS + " VARCHAR)");

        db.execSQL("create table " + TABLE_NAME_SEARTCH + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRODUCT_NAME + " VARCHAR, "
                + PRODUCT_ID + " VARCHAR)");

        db.execSQL("create table " + TABLE_NAME_STOCK + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRODUCT_ID + " VARCHAR, "
                + TATOL_STOCK + " VARCHAR, "
                + STOCK_RATS + " VARCHAR, "
                + USER_ID + " VARCHAR, "
                + DATE + " VARCHAR)");

        db.execSQL("create table " + TABLE_NAME_PURCHESS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRIDUCT_ID + " VARCHAR, "
                + PHARMACY_ID + " VARCHAR, "
                + NET_VALUE + " INTEGER, "
                + USER_ID_FOR_PURCHESS + " VARCHAR, "
                + PIECES + " INTEGER, "
                + TOTAL_VALUE + " INTEGER, "
                + DATE + " VARCHAR, "
                + INDENTY_VALUE + " VARCHAR)");

        db.execSQL("create table " + TABLE_UNPAID_AND_PAID + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " VARCHAR, "
                + MOBILE + " VARCHAR, "
                + PURCHASS + " INTEGER, "
                + TOTAL_TK + " VARCHAR, "
                + PAID_AMOUT + " INTEGER, "
                + DEBTS + " INTEGER, "
                + DATE + " VARCHAR)");



        db.execSQL("create table " + TABLE_STOCT_OUT_LIST + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PHARMARCY_ID + " VARCHAR, "
                + BRAND_ID + " VARCHAR, "
                + PRODUET_NAME + " VARCHAR, "
                + GANARIC_NAME + " VARCHAR, "
                + STROGEST_CAPASITY + " VARCHAR, "
                + TABLE_STOCT_LIST + " VARCHAR,"
                + DATE + " VARCHAR)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SEARTCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STOCK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PURCHESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCT_OUT_LIST);
        onCreate(db);
    }



    // internet to data syc
    public boolean insertRecord(String brand_name,String product_name,String granaric, String strenght,String table_eg, String product_id, String catalogs) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BRAND_NAME, brand_name);
        contentValues.put(PRODUCT_NAME, product_name);
        contentValues.put(GRANARIC, granaric);
        contentValues.put(STRENGTH, strenght);
        contentValues.put(TABLE_EG, table_eg);
        contentValues.put(PRODUCT_ID, product_id);
        contentValues.put(CATALOGS, catalogs);
        long result = database.insert(TABLE_NAME, null, contentValues);
        Log.e("ddddddddddddddddddddddddddddddd", String.valueOf(result));
        database.close();
        if (result == 1) {
            return false;
        } else {
        return true;
       }
    }


    // internet to data syc search data
   public boolean search_sqlite(String product_name, String product_id) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME, product_name);
        contentValues.put(PRODUCT_ID, product_id);
        long result = database.insert(TABLE_NAME_SEARTCH, null, contentValues);
        database.close();
        if (result == 1) {
            return false;
        } else {
        return true;
       }
    }


    // Stock table details
    public boolean stock_table(String product_id,String table_stock,String stock_rate,String user_id,String date) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product_id);
        contentValues.put(TATOL_STOCK, table_stock);
        contentValues.put(STOCK_RATS, stock_rate);
        contentValues.put(USER_ID, user_id);
        contentValues.put(DATE, date);
        long result = database.insert(TABLE_NAME_STOCK, null, contentValues);
        database.close();
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }



    // This funcation is prodct purchesa
    public boolean insert_pruchess(String priduct_id, String pharmacy_id, Double net_value, String username, int price, Double total_value, String indenty_value,String dates) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRIDUCT_ID, priduct_id);
        contentValues.put(PHARMACY_ID, pharmacy_id);
        contentValues.put(NET_VALUE, net_value);
        contentValues.put(USER_ID_FOR_PURCHESS, username);
        contentValues.put(PIECES, price);
        contentValues.put(TOTAL_VALUE, total_value);
        contentValues.put(INDENTY_VALUE, indenty_value);
        contentValues.put(DATE, dates);
        long result = database.insert(TABLE_NAME_PURCHESS, null, contentValues);
        database.close();
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }

    // This funcation is paid and unpaid
    public boolean paid_and_unpaid(String name, String mobile, String purchess, int total_tk, int paid_amount, int debts_tk , String dates) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(MOBILE, mobile);
        contentValues.put(PURCHASS, purchess);
        contentValues.put(TOTAL_TK, total_tk);
        contentValues.put(PAID_AMOUT, paid_amount);
        contentValues.put(DEBTS, debts_tk);
        contentValues.put(DATE, dates);
        long result = database.insert(TABLE_UNPAID_AND_PAID, null, contentValues);
        database.close();
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }





    // This funcation is stoct_out_list
    public boolean stoct_out_list(String prarmarcy_id, String brand_id, String product_name, String ganaric_name, String strogest, String table_stoct , String dates) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHARMARCY_ID, prarmarcy_id);
        contentValues.put(BRAND_ID, brand_id);
        contentValues.put(PRODUET_NAME, product_name);
        contentValues.put(GANARIC_NAME, ganaric_name);
        contentValues.put(STROGEST_CAPASITY, strogest);
        contentValues.put(TABLE_STOCT_LIST, table_stoct);
        contentValues.put(DATE, dates);
        long resultsss = database.insert(TABLE_STOCT_OUT_LIST, null, contentValues);
        database.close();
        if (resultsss == 1) {
            return false;
        } else {
            return true;
        }
    }

    // This funcation is stock table to update ..... stoct product golo update korar jnno use hoy
    public boolean stock_table_update(String product_id, String table_stock, Double rate) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product_id);
        contentValues.put(TATOL_STOCK, table_stock);
        contentValues.put(STOCK_RATS, rate);
        Log.e("Update ",product_id);
        long result = database.update(TABLE_NAME_STOCK, contentValues,PRODUCT_ID + "=?",new String[]{product_id});
        Log.e("result ", String.valueOf(result));
        database.close();
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean checkid(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where " + PRODUCT_ID + " = " + "'"+id+"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        else
        {
            cursor.close();
            return true;
        }
    }



    public ArrayList<SqliteContracter> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<SqliteContracter> campsites = new ArrayList<>();
        SqliteContracter campsite;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                campsite = new SqliteContracter();
                campsite.setIds(cursor.getString(1));
                campsite.setBrand_name(cursor.getString(2));
                campsite.setProduct_name(cursor.getString(3));
                campsite.setGeneric(cursor.getString(4));
                campsite.setStrength(cursor.getString(5));
                campsite.setForm_eg_table(cursor.getString(6));
         //       campsite.setCatalogs(cursor.getString(7));
                campsites.add(campsite);
            }
        }
        cursor.close();
        db.close();
        return campsites;
    }



    ArrayList<String> listContacts() {
        String sql = "select * from " + TABLE_NAME_SEARTCH;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String phno = cursor.getString(1);
                storeContacts.add(phno);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }


    public ArrayList<Product> getAllCampsites() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Product> campsites = new ArrayList<>();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String a=cursor.getString(6); //id
                String b=cursor.getString(1); //brand name
                String c=cursor.getString(2); // product name
                String d=cursor.getString(3); // ganaric
                String e=cursor.getString(4); //mg
                String f=cursor.getString(5); //tablet
                String g=cursor.getString(7); //cat id
                campsites.add(new Product(a, b,d,e, f, g,c));
//                Log.e("id",cursor.getString(0));
//                Log.e("brand name",cursor.getString(1));
//                Log.e("product name",cursor.getString(2));
//                Log.e("ganaric",cursor.getString(3));
//                Log.e("mg",cursor.getString(4));
//                Log.e("tablet",cursor.getString(5));
//                Log.e("cat id",cursor.getString(7));
            }
        }
        cursor.close();
        db.close();
        return campsites;
    }


    public ArrayList<Product> get_all_stoct_out_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STOCT_OUT_LIST, null);
        ArrayList<Product> campsites = new ArrayList<>();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String a=cursor.getString(1); //id
                String b=cursor.getString(2); //brand name
                String c=cursor.getString(3); // product name
                String d=cursor.getString(4); // ganaric
                String e=cursor.getString(5); //mg
                String f=cursor.getString(6); //tablet
                String g=cursor.getString(7); //cat id
                campsites.add(new Product(a, b,d,e, f, g,c));
//                Log.e("id",cursor.getString(0));
//                Log.e("brand name",cursor.getString(1));
//                Log.e("product name",cursor.getString(2));
//                Log.e("ganaric",cursor.getString(3));
//                Log.e("mg",cursor.getString(4));
//                Log.e("tablet",cursor.getString(5));
//                Log.e("cat id",cursor.getString(7));
            }
        }
        cursor.close();
        db.close();
        return campsites;
    }




//   Search database
    public Cursor arrayListstring(String a) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME_SEARTCH + " where " + PRODUCT_NAME + " = " + "'"+a+"'", null);
        return cursor;
    }

    //   all data  database
    public Cursor all_data (String a) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + PRODUCT_ID + " = " + "'"+a+"'", null);
        return cursor;
    }

    //   Stock database
    public Cursor stock_list(String a) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME_STOCK + " where " + PRODUCT_ID + " = " + "'"+a+"'", null);
        return cursor;
    }
// stock Out database
    public Cursor stock_out_data(String a) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + PRODUCT_ID + " = " + "'"+a+"'", null);
        return cursor;
    }

}


