# SQLite-Database-in-Android
SQLite database helper class which predefined method, need a small change to use in your project or understand the concept of SQLite database in android.

 ```java
public class DBHelper extends SQLiteOpenHelper 

   public static final String DATABASE_NAME = "Db_Unread.db";
    public static final String SMS_TABLE_NAME = "tb_sms";
    public static final String SMS_COLUMN_ID = "id";
    public static final String SMS_COLUMN_NUMBER = "sms_number";
    public static final String SMS_COLUMN_BODY = "sms_body";
    public static final String SMS_COLUMN_STATUS = "sms_status";
    public static final String SMS_COLUMN_DATETIME = "sms_datetime";
    private HashMap hp;
    
   
 super(context, DATABASE_NAME , null, 2);
 Create a database DATABASE_NAME = "Db_Unread.db"; with daatabse name Db_Unread.db.
  

    db.execSQL(
                "create table tb_sms " +
                "(id integer primary key, sms_number text,sms_body text,sms_status text,sms_datetime text)"
     );
     
     
 db.execSQL will create a table inside database ( Db_Unread.db ) which have five columns.
     
     
public boolean insertSms (String number, String body, String status, String date) { 
   db.insert(SMS_TABLE_NAME, null, contentValues);  
}

```

Insert new data or record to table.



public Integer deleteSms (Integer id) {}



delete record from from table the function require id for record.



public ArrayList<String> getAllSms() {
   SQLiteDatabase db = this.getReadableDatabase();
   Cursor res =  db.rawQuery( "select * from "+ SMS_TABLE_NAME , null );
}



Method will return all data from the table.



public ArrayList<String> getSentSms() {
   SQLiteDatabase db = this.getReadableDatabase();
   Cursor res =  db.rawQuery( "select * from "+ SMS_TABLE_NAME +" where sms_status = 0" , null );
}



Method will return record on the base of specific criteria which use WHERE Class.



  public ArrayList<String> getUnSentSms() {
  }
  
  Return Unsent SMS.

  public Cursor getSms(int id) {
  }
  
  
  only one record at a time  on the base of specific criteria which use WHERE (id) Class.
  
  
public int numberOfRows(){
}

return number of record exist in table.

public boolean updateSms (Integer id, String number, String body, String status, String date) {
}
   
   
Updata a record.

    

