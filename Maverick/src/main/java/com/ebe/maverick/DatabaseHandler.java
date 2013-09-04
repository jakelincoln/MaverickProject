package com.ebe.maverick;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by EBE13NKTW1 on 8/29/13.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String ANDROID_DATABASE_NAME = "applicantManager";

    private static final String ANDROID_TABLE_APPLICANTS = "applicants";

    private static final String ANDROID_KEY_ID = "rec_ID";
    private static final String ANDROID_SUBMITTED = "beenSubmitted";
    private static final String ANDROID_LEADSOURCE = "LeadSource";
    private static final String ANDROID_LASTNAME = "LastName";
    private static final String ANDROID_FIRSTNAME = "FirstName";
    private static final String ANDROID_MIDNAME = "MidName";
    private static final String ANDROID_SSN = "SSN";
    private static final String ANDROID_DOB = "DOB";
    private static final String ANDROID_ADDR = "Address";
    private static final String ANDROID_CITY = "City";
    private static final String ANDROID_ST = "State";
    private static final String ANDROID_ZIP = "Zip";
    private static final String ANDROID_HOMEPHONE = "HomePhone";
    private static final String ANDROID_CELLPHONE = "CellPhone";
    private static final String ANDROID_OTHERPHONE = "OtherPhone";
    private static final String ANDROID_EMAIL1 = "Email1";
    private static final String ANDROID_EMAIL2 = "Email2";
    private static final String ANDROID_EMAIL3 = "Email3";
    private static final String ANDROID_FAX = "Fax";
    private static final String ANDROID_CONTACTMETHOD = "ContactMethod";
    private static final String ANDROID_TIME = "Time";
    private static final String ANDROID_BATCHID = "BatchID";

    public DatabaseHandler(Context context){
        super(context, ANDROID_DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_APPLICANT_TABLE = "CREATE TABLE " + ANDROID_TABLE_APPLICANTS + "("
                + ANDROID_KEY_ID + "INTEGER PRIMARY KEY," + ANDROID_SUBMITTED + "INTEGER," + ANDROID_LEADSOURCE
                + "TEXT," + ANDROID_LASTNAME + "TEXT," + ANDROID_FIRSTNAME + "TEXT," + ANDROID_MIDNAME+ "TEXT," +
                ANDROID_SSN + "TEXT," + ANDROID_DOB + "TEXT," + ANDROID_ADDR + "TEXT," + ANDROID_CITY + "TEXT," + ANDROID_ST +
                "TEXT," + ANDROID_ZIP + "TEXT," + ANDROID_HOMEPHONE + "TEXT," + ANDROID_CELLPHONE + "TEXT," +
                ANDROID_OTHERPHONE + "TEXT," + ANDROID_EMAIL1 + "TEXT," + ANDROID_EMAIL2 + "TEXT," + ANDROID_EMAIL3 + "TEXT,"
                + ANDROID_FAX + "TEXT," + ANDROID_CONTACTMETHOD + "TEXT," + ANDROID_TIME + "TEXT," + ANDROID_BATCHID+ "INTEGER"
                + ")";
        db.execSQL(CREATE_APPLICANT_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ANDROID_TABLE_APPLICANTS);

        onCreate(db);
    }

    public void addApplicant(shortformSendApplicant applicant){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ANDROID_BATCHID, "");
        values.put(ANDROID_SUBMITTED, 0);
        values.put(ANDROID_LEADSOURCE, applicant.getSource());
        values.put(ANDROID_LASTNAME, applicant.getLName());
        values.put(ANDROID_FIRSTNAME, applicant.getFName());
        values.put(ANDROID_MIDNAME, applicant.getMName());
        values.put(ANDROID_SSN, applicant.getSSN());
        values.put(ANDROID_DOB, applicant.getDoB());
        values.put(ANDROID_ADDR, applicant.getAddr());
        values.put(ANDROID_CITY, applicant.getCity());
        values.put(ANDROID_ST, applicant.getState());
        values.put(ANDROID_ZIP, applicant.getZip());
        values.put(ANDROID_HOMEPHONE, applicant.getHomePhone());
        values.put(ANDROID_CELLPHONE, applicant.getCellPhone());
        values.put(ANDROID_OTHERPHONE, applicant.getOtherPhone());
        values.put(ANDROID_EMAIL1, applicant.getEmail());
        values.put(ANDROID_EMAIL2, applicant.getEmail2());
        values.put(ANDROID_EMAIL3, applicant.getEmail3());
        values.put(ANDROID_FAX, applicant.getFax());
        values.put(ANDROID_CONTACTMETHOD, applicant.getContactMethod());
        values.put(ANDROID_TIME, DateFormat.getDateTimeInstance().toString());

        db.insert(ANDROID_TABLE_APPLICANTS,null,values);
        db.close();

    }

    public shortformSendBatch getBatch(int batchID){

        shortformSendBatch batch = new shortformSendBatch(batchID);

        String selectBatchQuery = "SELECT * FROM " + ANDROID_TABLE_APPLICANTS +
                " WHERE " + ANDROID_BATCHID + "=" + batchID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectBatchQuery,null);

        if (cursor.moveToFirst()){
            do{
                shortformSendApplicant applicant = new shortformSendApplicant();
                applicant.setSource(cursor.getString(2));
                applicant.setLName(cursor.getString(3));
                applicant.setFName(cursor.getString(4));
                applicant.setMName(cursor.getString(5));
                applicant.setSSN(cursor.getString(6));
                applicant.setDoB(cursor.getString(7));
                applicant.setAddr(cursor.getString(8));
                applicant.setCity(cursor.getString(9));
                applicant.setState(cursor.getString(10));
                applicant.setZip(cursor.getString(11));
                applicant.setHomePhone(cursor.getString(12));
                applicant.setCellPhone(cursor.getString(13));
                applicant.setOtherPhone(cursor.getString(14));
                applicant.setEmail(cursor.getString(15));
                applicant.setEmail2(cursor.getString(16));
                applicant.setEmail3(cursor.getString(17));
                applicant.setFax(cursor.getString(18));
                applicant.setContactMethod(cursor.getString(19));
                applicant.setTime(cursor.getString(20));

                batch.addApplicant(applicant);
            }while (cursor.moveToNext());
        }
        db.close();
        return batch;
    }

    public void markAsSubmitted(int batchID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ANDROID_SUBMITTED,1);

        db.update(ANDROID_TABLE_APPLICANTS,values,ANDROID_BATCHID + " = ?",new String[] {Integer.toString(batchID)});
        db.close();
    }

    public void markAsSubmitted(ArrayList<Integer> batches){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ANDROID_SUBMITTED,1);

        for (Integer iterator : batches){
            db.update(ANDROID_TABLE_APPLICANTS,values,ANDROID_BATCHID + " = ?",new String[] {Integer.toString(iterator)});
        }
        db.close();
    }

    public ArrayList<Integer> getUnsubmittedBatches(){
        ArrayList<Integer> batches = new ArrayList<Integer>();

        SQLiteDatabase db = this.getWritableDatabase();
        String batchQuery = "SELECT DISTINCT " + ANDROID_BATCHID + " FROM " + ANDROID_TABLE_APPLICANTS
                + " WHERE " + ANDROID_SUBMITTED + " =0";
        Cursor cursor = db.rawQuery(batchQuery, null);

        if(cursor.moveToFirst()){
            do{
                batches.add(Integer.parseInt(cursor.getString(0)));
            }while (cursor.moveToNext());
        }

        db.close();
        return batches;
    }

    public boolean clearSubmitted(){
        boolean cleared = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String deleteSubmittedQuery = "DELETE FROM " + ANDROID_TABLE_APPLICANTS +" WHERE " + ANDROID_SUBMITTED + "= 1";
        try{
            db.rawQuery(deleteSubmittedQuery,null);
            cleared = true;

        }
        catch (Exception e){
            cleared = false;
        }
        db.close();
        return cleared;
    }

    public boolean assignBatchNum(){
        int highestBatch = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String highestBatchQuery = "SELECT MAX(" + ANDROID_BATCHID + ") FROM" + ANDROID_TABLE_APPLICANTS;
        try{
            Cursor cursor = db.rawQuery(highestBatchQuery,null);
            highestBatch = cursor.getInt(0);
            highestBatch++;
            ContentValues values = new ContentValues();
            values.put(ANDROID_BATCHID, highestBatch);
            db.update(ANDROID_TABLE_APPLICANTS,values,ANDROID_BATCHID + " = ?", new String[] {""});
            db.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
