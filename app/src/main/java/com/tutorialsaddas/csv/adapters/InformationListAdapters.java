package com.tutorialsaddas.csv.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tutorialsaddas.csv.DetailsViewActivity;
import com.tutorialsaddas.csv.R;
import com.tutorialsaddas.csv.model.Information;

import java.util.List;

public class InformationListAdapters extends RecyclerView.Adapter<InformationListAdapters.MyViewHolder> {

    private List<Information> informationList;

    String name,mrn_no,assoYr,fellyr, mobile1, mobile2, mobile3,phoneNew,phon3,res_phon,email1,email2,
            email3,profAddress,phon;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_view_name, txt_view_pin_code, txt_view_country_name,txt_view_mrn_no,txt_view_address,txt_view_state_name;
        LinearLayout linearlayout;

        public MyViewHolder(View view) {
            super(view);
            txt_view_name = (TextView) view.findViewById(R.id.txt_view_name);
            txt_view_pin_code = (TextView) view.findViewById(R.id.txt_view_pin_code);
            txt_view_country_name = (TextView) view.findViewById(R.id.txt_view_country_name);
            txt_view_mrn_no = (TextView) view.findViewById(R.id.txt_view_mrn_no);
            txt_view_address = (TextView) view.findViewById(R.id.txt_view_address);
            txt_view_state_name = (TextView) view.findViewById(R.id.txt_view_state_name);
            linearlayout =    view.findViewById(R.id.linearlayout);
           // year = (TextView) view.findViewById(R.id.year);
        }
    }


    public InformationListAdapters(List<Information> informationList) {
        this.informationList = informationList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_information_inflate, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final   int position) {

        final Information information = informationList.get(position);

        holder.txt_view_state_name.setText(information.getState());

        Log.d("", "fetchSingleData:2 "+information.getNewName());


        holder.txt_view_name.setText(information.getNewName());
        holder.txt_view_mrn_no.setText(information.getMrn());
        holder.txt_view_pin_code.setText(information.getPin());
        holder.txt_view_country_name.setText(information.getCountry());
        holder.txt_view_address.setText(information.getProfAdd());


        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information information = informationList.get(position);
                name=information.getNewName();
                mrn_no=information.getMrn();
                assoYr=information.getAssoyr();
                fellyr=information.getFellyr();
                mobile1=information.getMobile1();
                mobile2=information.getMobile2();
                mobile3=information.getMobile3();
                phoneNew=information.getPhonenew();
                phon=information.getPhone();
                res_phon=information.getResiPhone();
                email1=information.getEmail1();
                email2=information.getEmail2();
                email3=information.getEmail3();
                profAddress=information.getProfAdd();


                Intent i = new Intent(v.getContext(), DetailsViewActivity.class);
                i.putExtra("name", name);
                i.putExtra("mrn_no", mrn_no);
                i.putExtra("assoYr", assoYr);
                i.putExtra("fellyr", fellyr);
                i.putExtra("mobile1", mobile1);
                i.putExtra("mobile2", mobile2);
                i.putExtra("mobile3", mobile3);
                i.putExtra("phoneNew", phoneNew);
                i.putExtra("res_phon", res_phon);
                i.putExtra("phon", phon);
                i.putExtra("email1", email1);
                i.putExtra("email2", email2);
                i.putExtra("email3", email3);
                i.putExtra("profAddress", profAddress);
                v.getContext().startActivity(i);
            }
        });
//        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

}



