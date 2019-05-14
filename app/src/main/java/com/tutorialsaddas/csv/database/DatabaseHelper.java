package com.tutorialsaddas.csv.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tutorialsaddas.csv.model.Information;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DB_NAME="Information.db";
    public DatabaseHelper(Context context) {
        super(context, "Information.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, Field1 text,SerialNo text,RegionAug15 text, Aug17 text,Nameold text,Sex text,Qual text,mrn text, Addr1 text,Addr2 text,Addr3 text,Addr4 text,City text, Pin text,State text,Country text,af text,cp text,emp text,pa text,nri text,assoyr text, fellyr text,voterNo text,BoothNo text,BoothName text,email text,phone text,mobile text, NewName text,FName text,MName text,LName text,Designation text,Organisation text,email1 text,email2 text,email3 text,email4 text,email5 text,phonenew text,mobile1 text,mobile2 text,mobile3 text,ForeignNo text,FaxNo text,Website text,Religion text,ProfAdd text,ProfAdd1 text,FlatNo text,FloorNo text,BldgNo text,Society text,Road text,Area text,Landmark text,CityNew text,PinNew text,StateNew text,ResiAdd text,Resicity text,ResiPIN text,ResiState text,ResiPhone text,OfficeAdd text,Officecity text,OfficePIN text,OfficeState text,FreezeStat text,FreezeTxt text,Consideration text,DOB text,Remarks text,JainLName text,Status text,ChngStat text,UpdateTime text,UpdateBy text,ResiAdd1 text,RFlatNo text,RFloorNo text,RBldgNo text,RSociety text,RRoad text,RArea text,RLandmark text,Fieldupdate text,removed text)"
        );


       /*String createTable = "CREATE TABLE " + Information.tableName +
               " ( " +
               Information.id + " INTEGER PRIMARY KEY," +
               Information.mrn + " TEXT," +
               Information.af + " TEXT," +
               Information.assoyr + " TEXT," +
               Information.cp + " TEXT," +
               Information.country + " TEXT," +
               Information.designation + " TEXT," +
               Information.dob + " TEXT," +
               Information.email1 + " TEXT," +
               Information.email2 + " TEXT," +
               Information.email3 + " TEXT," +
               Information.emp + " TEXT," +
               Information.fellyr + " TEXT," +
               Information.nri + " TEXT," +
               Information.organisation + " TEXT," +
               Information.pa + " TEXT," +
               Information.phonenew + " TEXT," +
               Information.resiAdd + " TEXT," +
               Information.resiCity + " TEXT," +
               Information.resicountry + " TEXT," +
               Information.resiPhone + " TEXT," +
               Information.resiPIN + " TEXT," +
               Information.pin + " TEXT," +
               Information.profAdd + " TEXT," +
               Information.profCity + " TEXT," +
               Information.state + " TEXT," +
               Information.mobile1 + " TEXT," +
               Information.mobile2 + " TEXT," +
               Information.mobile3 + " TEXT," +
               Information.newName + " TEXT" + " ) ";*/

      // db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
