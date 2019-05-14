package com.tutorialsaddas.csv.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorialsaddas.csv.R;
import com.tutorialsaddas.csv.interfaces.DialogCallBack;

import java.util.zip.Inflater;

public class DialogUtils {
    public static void showForgetPasswordDialog(final AppCompatActivity context, final DialogCallBack dialogCallBack) {

        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_dialog_box, null);
        ad.setView(dialogView);

        final TextView txt_view_internal_storage = dialogView.findViewById(R.id.txt_view_internal_storage);
        final TextView txt_view_external_storage = dialogView.findViewById(R.id.txt_view_external_storage);

        final AlertDialog alertDialog = ad.create();
        alertDialog.setCanceledOnTouchOutside(true);
        txt_view_internal_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialogCallBack.onDialogClick(1);
                alertDialog.dismiss();
            }
        });


        txt_view_external_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(MethodUtils.isExternalStorageAvailable()){
                   dialogCallBack.onDialogClick(2);
                   alertDialog.dismiss();
               }else {
                   Toast.makeText(context, "External Storage Not Available", Toast.LENGTH_SHORT).show();
               }
            }
        });

        alertDialog.show();
    }
}

