package com.tutorialsaddas.csv;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorialsaddas.csv.adapters.InformationListAdapters;
import com.tutorialsaddas.csv.database.DatabaseHelper;
import com.tutorialsaddas.csv.database.TableQuery;
import com.tutorialsaddas.csv.interfaces.DialogCallBack;
import com.tutorialsaddas.csv.model.Information;
import com.tutorialsaddas.csv.utils.DialogUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class HomeActivity extends AppCompatActivity implements DialogCallBack {

    Button btnUpload, btn_read_file;
    TextView txtViewPath, mrn_txtView, pin_txtView, country_name_txtView;
    Uri path;
    EditText search_field_editTxt;
    Button btn_search;
    ProgressDialog progressDialog;
    public static final int REQUEST_CODE = 1;
    String field1, field2;
    Spinner field_spinner;
    RecyclerView recycler_view;
    int position;
    ArrayList<String> spinnerItem = new ArrayList<>();
    ArrayList<Information> informationArrayList;
    InformationListAdapters informationListAdapters;

    AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // btnUpload = findViewById(R.id.btn_upload);
        txtViewPath = findViewById(R.id.txt_view_path);
        btn_read_file = findViewById(R.id.btn_read_file);
        recycler_view = findViewById(R.id.recycler_view);
        search_field_editTxt = findViewById(R.id.search_field_editTxt);
        btn_search = findViewById(R.id.btn_search);

        field_spinner = findViewById(R.id.field_spinner);
        // selected_data_spinner = findViewById(R.id.selected_data_spinner);

        assetManager = getAssets();


      //  readExcelFileFromAssets();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                fileIntent.setType("application/vnd.ms-excel");
                try {
                    startActivityForResult(fileIntent, REQUEST_CODE);
                } catch (ActivityNotFoundException e) {
                    // lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkFilePermissions();
        }

        field_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                field1 = field_spinner.getItemAtPosition(field_spinner.getSelectedItemPosition()).toString();
                position = field_spinner.getSelectedItemPosition();
                Log.d("", "fetchData: " + field1);


                //Toast.makeText(getApplicationContext(), "" + field, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        btnReadFile();
        btn_searchClick();
    }


    public void readExcelFileFromAssets() {

        try {
            // Creating Input Stream
            /*
             * File file = new File( filename); FileInputStream myInput = new
             * FileInputStream(file);
             */

            InputStream myInput;

            //  Don't forget to Change to your assets folder excel sheet
            myInput = assetManager.open("newdata.xls");

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIterator = mySheet.iterator();

            while (rowIterator.hasNext()) {
                String[] result = new String[95];
                Row row = (Row) rowIterator.next();
                Iterator cellIterator = row.cellIterator();
                int i = 0;
                for (int j = 0; j < 91; j++) {

                    Cell cell = row.getCell(i);

                    if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                        result[++i] = "empty";
                    } else {
                        DataFormatter formatter = new DataFormatter();
                        String partNum = formatter.formatCellValue(cell);
                        Log.d("", "doInBackground: " + partNum);
                        result[++i] = partNum;
                    }

                    insertInformation(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    public void btnReadFile() {
        btn_read_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  File inputfile=new File(txtViewPath.getText().toString().trim());
                if (validationCheck() == true) {

                    DownloadFilesTask downloadFilesTask = new DownloadFilesTask();
                    downloadFilesTask.execute();
                }

            }
        });
    }

    @Override
    public void onDialogClick(int requestCode) {
        if (requestCode == 1) {


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {

            if (data != null) {
                if (resultCode == RESULT_OK) {
                    txtViewPath.setText(data.getData().getPath());
                    path = data.getData();
                }
            }
        }
    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream stream = null;
            try {
                stream = getContentResolver().openInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            HSSFWorkbook workbook;
            try {
                workbook = new HSSFWorkbook(stream);
                HSSFSheet sheet = workbook.getSheetAt(0);
                Iterator rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    String[] result = new String[95];
                    Row row = (Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();
                    int i = 0;
                    for (int j = 0; j < 91; j++) {

                        Cell cell = row.getCell(i);

                        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                            result[++i] = "empty";
                        } else {
                            DataFormatter formatter = new DataFormatter();
                            String partNum = formatter.formatCellValue(cell);
                            Log.d("", "doInBackground: " + partNum);
                            result[++i] = partNum;
                        }


                    }
                    insertInformation(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setTitle("Read Excel File..");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            Log.d("", "onCancelled: 1");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("", "onCancelled: 2");
        }
    }

    public void insertInformation(String[] list) {
        SQLiteDatabase database = null;
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        try {
            database = databaseHelper.getWritableDatabase();

        } catch (Exception e) {
            Log.d("", "insertInformation: " + e.getMessage());
        }


        ContentValues insertValues = new ContentValues();
        insertValues.put("Field1", list[1]);
        insertValues.put("SerialNo", list[2]);
        insertValues.put("RegionAug15", list[3]);
        insertValues.put("Aug17", list[4]);
        insertValues.put("Nameold", list[5]);
        insertValues.put("Sex", list[6]);
        insertValues.put("Qual", list[7]);
        insertValues.put("mrn", list[8]);
        insertValues.put("Addr1", list[9]);
        insertValues.put("Addr2", list[10]);
        insertValues.put("Addr3", list[11]);
        insertValues.put("Addr4", list[12]);
        insertValues.put("City", list[13]);
        insertValues.put("Pin", list[14]);
        insertValues.put("State", list[15]);
        insertValues.put("Country", list[16]);
        insertValues.put("af", list[17]);
        insertValues.put("cp", list[18]);
        insertValues.put("emp", list[19]);
        insertValues.put("pa", list[20]);
        insertValues.put("nri", list[21]);
        insertValues.put("assoyr", list[22]);
        insertValues.put("fellyr", list[23]);
        insertValues.put("voterNo", list[24]);
        insertValues.put("BoothNo", list[25]);
        insertValues.put("BoothName", list[26]);
        insertValues.put("email", list[27]);
        insertValues.put("phone", list[28]);
        insertValues.put("mobile", list[29]);
        insertValues.put("NewName", list[30]);
        insertValues.put("FName", list[31]);
        insertValues.put("MName", list[32]);
        insertValues.put("LName", list[33]);
        insertValues.put("Designation", list[34]);
        insertValues.put("Organisation", list[35]);
        insertValues.put("email1", list[36]);
        insertValues.put("email2", list[37]);
        insertValues.put("email3", list[38]);
        insertValues.put("email4", list[39]);
        insertValues.put("email5", list[40]);
        insertValues.put("phonenew", list[41]);
        insertValues.put("mobile1", list[42]);
        insertValues.put("mobile2", list[43]);
        insertValues.put("mobile3", list[44]);
        insertValues.put("ForeignNo", list[45]);
        insertValues.put("FaxNo", list[46]);
        insertValues.put("Website", list[47]);
        insertValues.put("Religion", list[48]);
        insertValues.put("ProfAdd", list[49]);
        insertValues.put("ProfAdd1", list[50]);
        insertValues.put("FlatNo", list[51]);
        insertValues.put("FloorNo", list[52]);
        insertValues.put("BldgNo", list[53]);
        insertValues.put("Society", list[54]);
        insertValues.put("Road", list[55]);
        insertValues.put("Area", list[56]);
        insertValues.put("Landmark", list[57]);
        insertValues.put("CityNew", list[58]);
        insertValues.put("PinNew", list[59]);
        insertValues.put("StateNew", list[60]);
        insertValues.put("ResiAdd", list[61]);
        insertValues.put("Resicity", list[62]);
        insertValues.put("ResiPIN", list[63]);
        insertValues.put("ResiState", list[64]);
        insertValues.put("ResiPhone", list[65]);
        insertValues.put("OfficeAdd", list[66]);
        insertValues.put("Officecity", list[67]);
        insertValues.put("OfficePIN", list[68]);
        insertValues.put("OfficeState", list[69]);
        insertValues.put("FreezeStat", list[70]);
        insertValues.put("FreezeTxt", list[71]);
        insertValues.put("Consideration", list[72]);
        insertValues.put("DOB", list[73]);
        insertValues.put("Remarks", list[74]);
        insertValues.put("JainLName", list[75]);
        insertValues.put("Status", list[76]);
        insertValues.put("ChngStat", list[77]);
        insertValues.put("UpdateTime", list[78]);
        insertValues.put("UpdateBy", list[79]);
        insertValues.put("ResiAdd1", list[80]);
        insertValues.put("RFlatNo", list[81]);
        insertValues.put("RFloorNo", list[82]);
        insertValues.put("RBldgNo", list[83]);
        insertValues.put("RSociety", list[84]);
        insertValues.put("RRoad", list[85]);
        insertValues.put("RArea", list[86]);
        insertValues.put("RLandmark", list[87]);
        insertValues.put("Fieldupdate", list[88]);
        insertValues.put("removed", list[89]);

        int yes = (int) database.insert("contacts", null, insertValues);
        Log.d("", "insertInformation: " + yes);

        database.close();
        databaseHelper.close();

    }


    public void fetchData() {
        SQLiteDatabase database = null;
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        try {
            database = databaseHelper.getWritableDatabase();

        } catch (Exception e) {
            Log.d("", "insertInformation: " + e.getMessage());
        }

        Log.d("", "fetchData: " + field1);

        //Toast.makeText(this, ""+field1, Toast.LENGTH_SHORT).show();

        String query1 = "select " + field1 + " from contacts";


        Cursor cursor = database.rawQuery(query1, null);

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String item = (cursor.getString(cursor.getColumnIndex(field1)));
                        spinnerItem.add(item);
                    } while (cursor.moveToNext());

                } else {

                }
            }
        } catch (Exception e) {
            Log.d("", "fetchCaseData: Exception Raised " + e);
        }

        cursor.close();
        database.close();
        //setDataToSecondSpinner();

    }


    /*public void setDataToSecondSpinner(){
      spinnerItem.add(0,"Select Item");
        selected_data_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                field2 = selected_data_spinner.getItemAtPosition(selected_data_spinner.getSelectedItemPosition()).toString();

              //  Toast.makeText(getApplicationContext(), "" + field1, Toast.LENGTH_LONG).show();
                if(field2.equalsIgnoreCase("Select Item")){

                }else{
                    fetchSingleData();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        selected_data_spinner.setAdapter(new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerItem));
    }*/


    public void fetchSingleData() {
        informationArrayList = new ArrayList<>();
        String query1;
        SQLiteDatabase database = null;
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        try {
            database = databaseHelper.getWritableDatabase();

        } catch (Exception e) {
            Log.d("s", "insertInformation: " + e.getMessage());
        }


            query1 = "select * from contacts where " + field1 + " = ?"+"COLLATE NOCASE";


        Cursor cursor = database.rawQuery(query1, new String[]{field2});
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                        String Field1 = (cursor.getString(cursor.getColumnIndex("Field1")));
                        String SerialNo = (cursor.getString(cursor.getColumnIndex("SerialNo")));
                        String newName = (cursor.getString(cursor.getColumnIndex("RegionAug15")));
                        String Aug17 = (cursor.getString(cursor.getColumnIndex("Aug17")));
                        String Nameold = (cursor.getString(cursor.getColumnIndex("Nameold")));
                        String Sex = (cursor.getString(cursor.getColumnIndex("Sex")));
                        String Qual = (cursor.getString(cursor.getColumnIndex("Qual")));
                        String mrn = (cursor.getString(cursor.getColumnIndex("mrn")));
                        String Addr1 = (cursor.getString(cursor.getColumnIndex("Addr1")));
                        String Addr2 = (cursor.getString(cursor.getColumnIndex("Addr2")));
                        String Addr3 = (cursor.getString(cursor.getColumnIndex("Addr3")));
                        String Addr4 = (cursor.getString(cursor.getColumnIndex("Addr4")));
                        String City = (cursor.getString(cursor.getColumnIndex("City")));
                        String Pin = (cursor.getString(cursor.getColumnIndex("Pin")));
                        String State = (cursor.getString(cursor.getColumnIndex("State")));
                        String Country = (cursor.getString(cursor.getColumnIndex("Country")));
                        String af = (cursor.getString(cursor.getColumnIndex("af")));
                        String cp = (cursor.getString(cursor.getColumnIndex("cp")));
                        String emp = (cursor.getString(cursor.getColumnIndex("emp")));
                        String pa = (cursor.getString(cursor.getColumnIndex("pa")));
                        String nri = (cursor.getString(cursor.getColumnIndex("nri")));
                        String assoyr = (cursor.getString(cursor.getColumnIndex("assoyr")));
                        String fellyr = (cursor.getString(cursor.getColumnIndex("fellyr")));
                        String voterNo = (cursor.getString(cursor.getColumnIndex("voterNo")));
                        String BoothNo = (cursor.getString(cursor.getColumnIndex("BoothNo")));
                        String BoothName = (cursor.getString(cursor.getColumnIndex("BoothName")));
                        String email = (cursor.getString(cursor.getColumnIndex("email")));
                        String phone = (cursor.getString(cursor.getColumnIndex("phone")));
                        String mobile = (cursor.getString(cursor.getColumnIndex("mobile")));
                        String NewName = (cursor.getString(cursor.getColumnIndex("NewName")));
                        String FName = (cursor.getString(cursor.getColumnIndex("FName")));
                        String MName = (cursor.getString(cursor.getColumnIndex("MName")));
                        String LName = (cursor.getString(cursor.getColumnIndex("LName")));
                        String Designation = (cursor.getString(cursor.getColumnIndex("Designation")));
                        String Organisation = (cursor.getString(cursor.getColumnIndex("Organisation")));
                        String email1 = (cursor.getString(cursor.getColumnIndex("email1")));
                        String email2 = (cursor.getString(cursor.getColumnIndex("email2")));
                        String email3 = (cursor.getString(cursor.getColumnIndex("email3")));
                        String email4 = (cursor.getString(cursor.getColumnIndex("email4")));
                        String email5 = (cursor.getString(cursor.getColumnIndex("email5")));
                        String phonenew = (cursor.getString(cursor.getColumnIndex("phonenew")));
                        String mobile1 = (cursor.getString(cursor.getColumnIndex("mobile1")));
                        String mobile2 = (cursor.getString(cursor.getColumnIndex("mobile2")));
                        String mobile3 = (cursor.getString(cursor.getColumnIndex("mobile3")));
                        String ForeignNo = (cursor.getString(cursor.getColumnIndex("ForeignNo")));
                        String FaxNo = (cursor.getString(cursor.getColumnIndex("FaxNo")));
                        String Website = (cursor.getString(cursor.getColumnIndex("Website")));
                        String Religion = (cursor.getString(cursor.getColumnIndex("Religion")));
                        String ProfAdd = (cursor.getString(cursor.getColumnIndex("ProfAdd")));
                        String ProfAdd1 = (cursor.getString(cursor.getColumnIndex("ProfAdd1")));
                        String FlatNo = (cursor.getString(cursor.getColumnIndex("FlatNo")));
                        String FloorNo = (cursor.getString(cursor.getColumnIndex("FloorNo")));
                        String BldgNo = (cursor.getString(cursor.getColumnIndex("BldgNo")));
                        String Society = (cursor.getString(cursor.getColumnIndex("Society")));
                        String Road = (cursor.getString(cursor.getColumnIndex("Road")));
                        String Area = (cursor.getString(cursor.getColumnIndex("Area")));
                        String Landmark = (cursor.getString(cursor.getColumnIndex("Landmark")));
                        String CityNew = (cursor.getString(cursor.getColumnIndex("CityNew")));
                        String PinNew = (cursor.getString(cursor.getColumnIndex("PinNew")));
                        String StateNew = (cursor.getString(cursor.getColumnIndex("StateNew")));
                        String ResiAdd = (cursor.getString(cursor.getColumnIndex("ResiAdd")));
                        String Resicity = (cursor.getString(cursor.getColumnIndex("Resicity")));
                        String ResiPIN = (cursor.getString(cursor.getColumnIndex("ResiPIN")));
                        String ResiState = (cursor.getString(cursor.getColumnIndex("ResiState")));
                        String ResiPhone = (cursor.getString(cursor.getColumnIndex("ResiPhone")));
                        String OfficeAdd = (cursor.getString(cursor.getColumnIndex("OfficeAdd")));
                        String Officecity = (cursor.getString(cursor.getColumnIndex("Officecity")));
                        String OfficePIN = (cursor.getString(cursor.getColumnIndex("OfficePIN")));
                        String OfficeState = (cursor.getString(cursor.getColumnIndex("OfficeState")));
                        String FreezeStat = (cursor.getString(cursor.getColumnIndex("FreezeStat")));
                        String FreezeTxt = (cursor.getString(cursor.getColumnIndex("FreezeTxt")));
                        String Consideration = (cursor.getString(cursor.getColumnIndex("Consideration")));
                        String DOB = (cursor.getString(cursor.getColumnIndex("DOB")));
                        String Remarks = (cursor.getString(cursor.getColumnIndex("Remarks")));
                        String JainLName = (cursor.getString(cursor.getColumnIndex("JainLName")));
                        String Status = (cursor.getString(cursor.getColumnIndex("Status")));
                        String ChngStat = (cursor.getString(cursor.getColumnIndex("ChngStat")));
                        String UpdateTime = (cursor.getString(cursor.getColumnIndex("UpdateTime")));
                        String UpdateBy = (cursor.getString(cursor.getColumnIndex("UpdateBy")));
                        String ResiAdd1 = (cursor.getString(cursor.getColumnIndex("ResiAdd1")));
                        String RFlatNo = (cursor.getString(cursor.getColumnIndex("RFlatNo")));
                        String RFloorNo = (cursor.getString(cursor.getColumnIndex("RFloorNo")));
                        String RBldgNo = (cursor.getString(cursor.getColumnIndex("RBldgNo")));
                        String RSociety = (cursor.getString(cursor.getColumnIndex("RSociety")));
                        String RRoad = (cursor.getString(cursor.getColumnIndex("RRoad")));
                        String RArea = (cursor.getString(cursor.getColumnIndex("RArea")));
                        String RLandmark = (cursor.getString(cursor.getColumnIndex("RLandmark")));
                        String Fieldupdate = (cursor.getString(cursor.getColumnIndex("Fieldupdate")));
                        String removed = (cursor.getString(cursor.getColumnIndex("removed")));


                        Information information = new Information();
                        information.setField1(Field1);
                        information.setSerialNo(SerialNo);
                        information.setNewName(newName);
                        information.setAug17(Aug17);
                        information.setNameold(Nameold);
                        information.setSex(Sex);
                        information.setQual(Qual);
                        information.setMrn(mrn);
                        information.setAddr1(Addr1);
                        information.setAddr2(Addr2);
                        information.setAddr3(Addr3);
                        information.setAddr4(Addr4);
                        information.setCity(City);
                        information.setPin(Pin);
                        information.setState(State);
                        information.setCountry(Country);
                        information.setAf(af);
                        information.setCp(cp);
                        information.setEmp(emp);
                        information.setPa(pa);
                        information.setNri(nri);
                        information.setAssoyr(assoyr);
                        information.setFellyr(fellyr);
                        information.setVoterNo(voterNo);
                        information.setBoothNo(BoothNo);
                        information.setBoothName(BoothName);
                        information.setEmail(email);
                        information.setPhone(phone);
                        information.setMobile(mobile);
                        information.setNewName(NewName);
                        information.setFName(FName);
                        information.setMName(MName);
                        information.setLName(LName);
                        information.setDesignation(Designation);
                        information.setOrganisation(Organisation);
                        information.setEmail1(email1);
                        information.setEmail2(email2);
                        information.setEmail3(email3);
                        information.setEmail4(email4);
                        information.setEmail5(email5);
                        information.setPhonenew(phonenew);
                        information.setMobile1(mobile1);
                        information.setMobile2(mobile2);
                        information.setMobile3(mobile3);
                        information.setForeignNo(ForeignNo);
                        information.setFaxNo(FaxNo);
                        information.setWebsite(Website);
                        information.setReligion(Religion);
                        information.setProfAdd(ProfAdd);
                        information.setProfAdd1(ProfAdd1);
                        information.setFlatNo(FlatNo);
                        information.setFloorNo(FloorNo);
                        information.setBldgNo(BldgNo);
                        information.setSociety(Society);
                        information.setRoad(Road);
                        information.setArea(Area);
                        information.setLandmark(Landmark);
                        information.setCityNew(CityNew);
                        information.setPinNew(PinNew);
                        information.setStateNew(StateNew);
                        information.setResiAdd(ResiAdd);
                        information.setResicity(Resicity);
                        information.setResiPIN(ResiPIN);
                        information.setResiState(ResiState);
                        information.setResiPhone(ResiPhone);
                        information.setOfficeAdd(OfficeAdd);
                        information.setOfficecity(Officecity);
                        information.setOfficePIN(OfficePIN);
                        information.setOfficeState(OfficeState);
                        information.setFreezeStat(FreezeStat);
                        information.setFreezeTxt(FreezeTxt);
                        information.setConsideration(Consideration);
                        information.setDOB(DOB);
                        information.setRemarks(Remarks);
                        information.setJainLName(JainLName);
                        information.setStatus(Status);
                        information.setChngStat(ChngStat);
                        information.setUpdateTime(UpdateTime);
                        information.setUpdateBy(UpdateBy);
                        information.setResiAdd1(ResiAdd1);
                        information.setRFlatNo(RFlatNo);
                        information.setRFloorNo(RFloorNo);
                        information.setRBldgNo(RBldgNo);
                        information.setRSociety(RSociety);
                        information.setRRoad(RRoad);
                        information.setRArea(RArea);
                        information.setRLandmark(RLandmark);
                        information.setFieldupdate(Fieldupdate);
                        information.setRemoved(removed);

                        informationArrayList.add(information);

                    }
                    if (informationListAdapters != null) {
                        informationListAdapters.notifyDataSetChanged();
                    }


                }
            } else {
                Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("", "fetchCaseData: Exception Raised " + e);
        }

        cursor.close();
        database.close();
        setAdapter();

    }

    public void btn_searchClick() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (field1.equalsIgnoreCase("Select Item")) {
                    Toast.makeText(HomeActivity.this, "Select Spinner item first", Toast.LENGTH_SHORT).show();
                } else {
                    if (search_field_editTxt.getText().toString().trim().equals("")) {
                        Toast.makeText(HomeActivity.this, "Search field cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        if (informationArrayList != null) {
                            if (informationArrayList.size() != 0) {
                                informationArrayList.clear();
                            }
                        }

                        field2 = search_field_editTxt.getText().toString();
                        fetchSingleData();

//                        if(informationListAdapters!=null){
//                            informationListAdapters.notifyDataSetChanged();
//                        }
                    }
                }


            }
        });
    }

    public void setAdapter() {

        informationListAdapters = new InformationListAdapters(informationArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(informationListAdapters);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        } else {
            Log.d("", "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    public boolean validationCheck() {
        boolean check = false;

        String path = txtViewPath.getText().toString().trim();

        if (check == false) {

            if (path.equals("")) {
                Toast.makeText(this, "Choose Excel path first", Toast.LENGTH_SHORT).show();
            } else {
                check = true;
            }
        }
        return check;
    }
}