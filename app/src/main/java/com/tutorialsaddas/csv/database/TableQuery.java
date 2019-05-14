package com.tutorialsaddas.csv.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.tutorialsaddas.csv.DatabaseSingleton;
import com.tutorialsaddas.csv.model.Information;

import java.util.ArrayList;

public class TableQuery {

   /* public static String createInformationTable() {

        String createTable = "CREATE TABLE " + Information.tableName +
                " ( " +
                Information.id + " INTEGER PRIMARY KEY," +
                Information.Field1 + " TEXT," +
                Information.SerialNo + " TEXT," +
                Information.RegionAug15 + " TEXT," +
                Information.Aug17 + " TEXT," +
                Information.Nameold + " TEXT," +
                Information.Sex + " TEXT," +
                Information.Qual + " TEXT," +
                Information.mrn + " TEXT," +
                Information.Addr1 + " TEXT," +
                Information.Addr2 + " TEXT," +
                Information.Addr3 + " TEXT," +
                Information.Addr4 + " TEXT," +
                Information.City + " TEXT," +
                Information.Pin + " TEXT," +
                Information.State + " TEXT," +
                Information.Country + " TEXT," +
                Information.af + " TEXT," +
                Information.cp + " TEXT," +
                Information.emp + " TEXT," +
                Information.pa + " TEXT," +
                Information.nri + " TEXT," +
                Information.assoyr + " TEXT," +
                Information.fellyr + " TEXT," +
                Information.voterNo + " TEXT," +
                Information.BoothNo + " TEXT," +
                Information.BoothName + " TEXT," +
                Information.email + " TEXT," +
                Information.phone + " TEXT," +
                Information.mobile + " TEXT," +
                Information.NewName + " TEXT," +
                Information.FName + " TEXT," +
                Information.MName + " TEXT," +
                Information.LName + " TEXT," +
                Information.Designation + " TEXT," +
                Information.Organisation + " TEXT," +
                Information.email1 + " TEXT," +
                Information.email2 + " TEXT," +
                Information.email3 + " TEXT," +
                Information.email4 + " TEXT," +
                Information.email5 + " TEXT," +
                Information.phonenew + " TEXT," +
                Information.mobile1 + " TEXT," +
                Information.mobile2 + " TEXT," +
                Information.mobile3 + " TEXT," +
                Information.ForeignNo + " TEXT," +
                Information.FaxNo + " TEXT," +
                Information.Website + " TEXT," +
                Information.Religion + " TEXT," +
                Information.ProfAdd + " TEXT," +
                Information.ProfAdd1 + " TEXT," +
                Information.FlatNo + " TEXT," +
                Information.FloorNo + " TEXT," +
                Information.BldgNo + " TEXT," +
                Information.Society + " TEXT," +
                Information.Road + " TEXT," +
                Information.Area + " TEXT," +
                Information.Landmark + " TEXT," +
                Information.CityNew + " TEXT," +
                Information.PinNew + " TEXT," +
                Information.StateNew + " TEXT," +
                Information.ResiAdd + " TEXT," +
                Information.Resicity + " TEXT," +
                Information.ResiPIN + " TEXT," +
                Information.ResiState + " TEXT," +
                Information.ResiPhone + " TEXT," +
                Information.OfficeAdd + " TEXT," +
                Information.Officecity + " TEXT," +
                Information.OfficePIN + " TEXT," +
                Information.OfficeState + " TEXT," +
                Information.FreezeStat + " TEXT," +
                Information.FreezeTxt + " TEXT," +
                Information.Consideration + " TEXT," +
                Information.DOB + " TEXT," +
                Information.Remarks + " TEXT," +
                Information.JainLName + " TEXT," +
                Information.Status + " TEXT," +
                Information.ChngStat + " TEXT," +
                Information.UpdateTime + " TEXT," +
                Information.UpdateBy + " TEXT," +
                Information.ResiAdd1 + " TEXT," +
                Information.RFlatNo + " TEXT," +
                Information.RFloorNo + " TEXT," +
                Information.RBldgNo + " TEXT," +
                Information.RSociety + " TEXT," +
                Information.RRoad + " TEXT," +
                Information.RArea + " TEXT," +
                Information.RLandmark + " TEXT," +
                Information.Fieldupdate + " TEXT," +
                Information.removed + " TEXT" + " ) ";

        return createTable;
    }

    public static void insertInformation(Context context, ArrayList<Information> list) {
        SQLiteDatabase database = null;
        DatabaseHelper databaseHelper = DatabaseSingleton.getInstance().getDatabaseInstance(context);

        try {
            database = databaseHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.d("", "insertInformation: " + e.getMessage());
        }

        for (int i = 0; i < list.size(); i++) {
            Information information = list.get(i);
            ContentValues insertValues = new ContentValues();
            insertValues.put(Information.mrn, information.getMrn());
//            insertValues.put(Information.newName, information.getNewName());
//            insertValues.put(Information.af, information.getAf());
//            insertValues.put(Information.assoyr, information.getAssoyr());
//            insertValues.put(Information.country, information.getCountry());
//            insertValues.put(Information.designation, information.getDesignation());
//            insertValues.put(Information.dob, information.getDob());
//            insertValues.put(Information.email1, information.getEmail1());
//            insertValues.put(Information.email2, information.getEmail2());
//            insertValues.put(Information.email3, information.getEmail3());
//            insertValues.put(Information.emp, information.getEmp());
//            insertValues.put(Information.fellyr, information.getFellyr());
//            insertValues.put(Information.nri, information.getNri());
//            insertValues.put(Information.organisation, information.getOrganisation());
//            insertValues.put(Information.pa, information.getPa());
//            insertValues.put(Information.phonenew, information.getPhonenew());
//            insertValues.put(Information.resiAdd, information.getResiAdd());
//            insertValues.put(Information.resiCity, information.getResiCity());
//            insertValues.put(Information.resicountry, information.getResicountry());
//            insertValues.put(Information.resiPhone, information.getResiPhone());
//            insertValues.put(Information.resiPIN, information.getResiPIN());
//            insertValues.put(Information.pin, information.getPin());
//            insertValues.put(Information.profAdd, information.getProfAdd());
//            insertValues.put(Information.profCity, information.getProfCity());
//            insertValues.put(Information.state, information.getState());
//            insertValues.put(Information.mobile1, information.getMobile1());
//            insertValues.put(Information.mobile2, information.getMobile2());
//            insertValues.put(Information.mobile3, information.getMobile3());
//            insertValues.put(Information.newName, information.getNewName());
        database.insert(Information.tableName, null, insertValues);

        }

        database.close();
        databaseHelper.close();

    }*/
}
