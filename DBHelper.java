package com.example.qiyamuddinahmadzai.sms006;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Qiyamuddin Ahmadzai on 2/27/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Db_Unread.db";
    public static final String SMS_TABLE_NAME = "tb_sms";
    public static final String SMS_COLUMN_ID = "id";
    public static final String SMS_COLUMN_NUMBER = "sms_number";
    public static final String SMS_COLUMN_BODY = "sms_body";
    public static final String SMS_COLUMN_STATUS = "sms_status";
    public static final String SMS_COLUMN_DATETIME = "sms_datetime";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table tb_sms " +
                "(id integer primary key, sms_number text,sms_body text,sms_status text,sms_datetime text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tb_sms");
        onCreate(db);
    }

    public boolean insertSms (String number, String body, String status, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SMS_COLUMN_NUMBER, number);
        contentValues.put(SMS_COLUMN_BODY, body);
        contentValues.put(SMS_COLUMN_STATUS, status);
        contentValues.put(SMS_COLUMN_DATETIME, date);

        db.insert(SMS_TABLE_NAME, null, contentValues);
        return true;
    }

    public Integer deleteSms (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SMS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }


    public ArrayList<String> getAllSms() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ SMS_TABLE_NAME , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(
                                 res.getColumnIndex(SMS_COLUMN_NUMBER))+" > "+
                                 res.getString(res.getColumnIndex(SMS_COLUMN_BODY))+" > "+
                                 res.getString(res.getColumnIndex(SMS_COLUMN_STATUS))+" > "+
                                 res.getString(res.getColumnIndex(SMS_COLUMN_DATETIME))
                            );
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getSentSms() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ SMS_TABLE_NAME +" where sms_status = 0" , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(
                           res.getString(res.getColumnIndex(SMS_COLUMN_NUMBER))+" > "+
                           res.getString(res.getColumnIndex(SMS_COLUMN_BODY))+" > "+
                           res.getString(res.getColumnIndex(SMS_COLUMN_STATUS))+" > "+
                           res.getString(res.getColumnIndex(SMS_COLUMN_DATETIME))
                        );
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getUnSentSms() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ SMS_TABLE_NAME +" where sms_status = 1" , null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(
                           res.getString(res.getColumnIndex(SMS_COLUMN_NUMBER))+" > "+
                           res.getString(res.getColumnIndex(SMS_COLUMN_BODY))+" > "+
                           res.getString(res.getColumnIndex(SMS_COLUMN_STATUS))+" > "+
                           res.getString(res.getColumnIndex(SMS_COLUMN_DATETIME))
                        );
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getSms(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tb_sms where id="+ id +"", null );
        return res;
    }


    public int getSmsStatus(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tb_sms where id="+ id +"", null );
        res.moveToNext();
        String status = res.getString(res.getColumnIndex(SMS_COLUMN_STATUS));
        return Integer.parseInt(status);
    }


    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SMS_TABLE_NAME);
        return numRows;
    }

    public boolean updateSms (Integer id, String number, String body, String status, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SMS_COLUMN_NUMBER, number);
        contentValues.put(SMS_COLUMN_BODY, body);
        contentValues.put(SMS_COLUMN_STATUS, status);
        contentValues.put(SMS_COLUMN_DATETIME, date);

        db.update(SMS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }




}
