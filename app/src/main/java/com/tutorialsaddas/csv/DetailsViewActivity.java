package com.tutorialsaddas.csv;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsViewActivity extends AppCompatActivity {


    TextView name_txtView, mrn_no_txtView, assoYr_txtView, fellyr_txtView, mobile1_txtview, mobile2_txtview, mobile3_txtview,
            phoneNew_txtview, phon3_txtView, res_phon_txtView, email1_txtview, email2_txtview, email3_txtview, profAddress_txtView;

    String name, mrn_no, assoYr, fellyr, mobile1, mobile2, mobile3, phoneNew, phon3, res_phon, email1, email2, email3, profAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        name_txtView = findViewById(R.id.name_txtView);
        mrn_no_txtView = findViewById(R.id.mrn_no_txtView);
        assoYr_txtView = findViewById(R.id.assoYr_txtView);
        fellyr_txtView = findViewById(R.id.fellyr_txtView);
        mobile1_txtview = findViewById(R.id.mobile1_txtview);
        mobile2_txtview = findViewById(R.id.mobile2_txtview);
        mobile3_txtview = findViewById(R.id.mobile3_txtview);
        phoneNew_txtview = findViewById(R.id.phoneNew_txtview);
        phon3_txtView = findViewById(R.id.phon3_txtView);
        res_phon_txtView = findViewById(R.id.res_phon_txtView);
        email1_txtview = findViewById(R.id.email1_txtview);
        email2_txtview = findViewById(R.id.email2_txtview);
        email3_txtview = findViewById(R.id.email3_txtview);
        profAddress_txtView = findViewById(R.id.profAddress_txtView);


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        mrn_no = intent.getStringExtra("mrn_no");
        assoYr = intent.getStringExtra("assoYr");
        fellyr = intent.getStringExtra("fellyr");
        mobile1 = intent.getStringExtra("mobile1");
        mobile2 = intent.getStringExtra("mobile2");
        mobile3 = intent.getStringExtra("mobile3");
        phon3 = intent.getStringExtra("phon");
        phoneNew = intent.getStringExtra("phoneNew");
        res_phon = intent.getStringExtra("res_phon");
        email1 = intent.getStringExtra("email1");
        email2 = intent.getStringExtra("email2");
        email3 = intent.getStringExtra("email3");
        profAddress = intent.getStringExtra("profAddress");


        name_txtView.setText(name);
        mrn_no_txtView.setText(mrn_no);
        assoYr_txtView.setText(assoYr);
        fellyr_txtView.setText(fellyr);
        mobile1_txtview.setText(mobile1);
        mobile2_txtview.setText(mobile2);
        mobile3_txtview.setText(mobile3);
        phoneNew_txtview.setText(phoneNew);
        res_phon_txtView.setText(res_phon);
        phon3_txtView.setText(phon3);
        email1_txtview.setText(email1);
        email2_txtview.setText(email2);
        email3_txtview.setText(email3);
        profAddress_txtView.setText(profAddress);


        mobile1_txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobile1.length()==10)
                {
                    showCallMessageDialog();

                }else {
                    Toast.makeText(DetailsViewActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }

            }
        });


        mobile2_txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile2.length()==10)
                {
                    showCallMessageDialog1();

                }else {
                    Toast.makeText(DetailsViewActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }

            }
        });


        mobile3_txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile3.length()==10)
                {
                    showCallMessageDialog2();

                }else {
                    Toast.makeText(DetailsViewActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showCallMessageDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(DetailsViewActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Call",
                "Message"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ChooseCall();
                                break;
                            case 1:
                                ChooseMessage();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void ChooseCall() {
        if (PackageManager.PERMISSION_GRANTED
                == ActivityCompat.checkSelfPermission(DetailsViewActivity.this,
                Manifest.permission.CALL_PHONE)) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mobile1));
            startActivity(callIntent);

        } else {
            requestWritePermission(DetailsViewActivity.this);

        }
    }

    private void ChooseMessage() {
        if (PackageManager.PERMISSION_GRANTED
                == ActivityCompat.checkSelfPermission(DetailsViewActivity.this,
                Manifest.permission.SEND_SMS)) {


            Uri uri = Uri.parse("smsto:"+mobile1);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", "");
            startActivity(it);

        } else {

            requestWritePermission(DetailsViewActivity.this);
        }
    }


    private void showCallMessageDialog1() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(DetailsViewActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Call",
                "Message"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ChooseCall1();
                                break;
                            case 1:
                                ChooseMessage1();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void ChooseCall1() {
        if (PackageManager.PERMISSION_GRANTED
                == ActivityCompat.checkSelfPermission(DetailsViewActivity.this,
                Manifest.permission.CALL_PHONE)) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mobile2));
            startActivity(callIntent);

        } else {
            requestWritePermission(DetailsViewActivity.this);

        }
    }

    private void ChooseMessage1() {
        if (PackageManager.PERMISSION_GRANTED
                == ActivityCompat.checkSelfPermission(DetailsViewActivity.this,
                Manifest.permission.SEND_SMS)) {


            Uri uri = Uri.parse("smsto:"+mobile2);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", "");
            startActivity(it);

        } else {

            requestWritePermission(DetailsViewActivity.this);
        }
    }

    private void showCallMessageDialog2() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(DetailsViewActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Call",
                "Message"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ChooseCall2();
                                break;
                            case 1:
                                ChooseMessage2();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void ChooseCall2() {
        if (PackageManager.PERMISSION_GRANTED
                == ActivityCompat.checkSelfPermission(DetailsViewActivity.this,
                Manifest.permission.CALL_PHONE)) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mobile3));
            startActivity(callIntent);

        } else {
            requestWritePermission(DetailsViewActivity.this);

        }
    }

    private void ChooseMessage2() {
        if (PackageManager.PERMISSION_GRANTED
                == ActivityCompat.checkSelfPermission(DetailsViewActivity.this,
                Manifest.permission.SEND_SMS)) {


            Uri uri = Uri.parse("smsto:"+mobile3);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", "");
            startActivity(it);

        } else {

            requestWritePermission(DetailsViewActivity.this);
        }
    }





    private static void requestWritePermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE)) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);

        } else if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.SEND_SMS)) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, 1);

        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, 1);
        }
    }

}
