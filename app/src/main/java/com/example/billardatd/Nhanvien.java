package com.example.billardatd;

import static android.util.Log.println;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class Nhanvien extends AppCompatActivity {

    Connection connect;
    String ConnectionResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        getSupportActionBar().hide();
        ImageView buttonback = (ImageView) findViewById(R.id.Back_NhanVien);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Nhanvien.super.onBackPressed();
                Intent intent = new Intent(Nhanvien.this,DashBoard.class);
                startActivity(intent);
                finish();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Nhanvien.this,"Back Success",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        ImageView searchnhanvien = (ImageView) findViewById(R.id.Search_ButtonNhanVien);
        TextView Tx_fullname = (TextView) findViewById(R.id.TextView_Fullname);
        TextView Tx_phone = (TextView) findViewById(R.id.Textview_Phone);
        TextView Tx_status = (TextView) findViewById(R.id.Textview_Status);
        EditText edittext_nhanvien = (EditText) findViewById(R.id.Edittext_SearchNhanVien);
        searchnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if (connect!=null){
                        String query = "select * from Account where name like '%"+edittext_nhanvien.getText().toString()+"%'";
                        Statement st= connect.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        while (rs.next())
                        {
                            Tx_fullname.setText(rs.getString(3));
                            Tx_fullname.setTextColor(getResources().getColor(R.color.NAME));
                            //Tx_gmail.setText(rs.getString(2));
                            Tx_phone.setText(rs.getString(6));
                            Tx_status.setText(rs.getString(9));
                            String thaydoi = Tx_status.getText().toString();
                            int thaydoi1 = Integer.parseInt(thaydoi);
                            if (thaydoi1 == 0){
                                Tx_status.setText("OFF");
                                Tx_status.setTextColor(getResources().getColor(R.color.OFF));
                            }
                            else{
                                Tx_status.setText("ON");
                                Tx_status.setTextColor(getResources().getColor(R.color.ON));
                            }



                        }
                    }
                    else
                    {
                        ConnectionResult= "Check Connection";
                    }

                } catch (Exception ex)
                {

                }

            }

        });

    }

    SimpleAdapter ad;

    public void GetList(View v) {
//        TextView TrangthaiListView = (TextView) findViewById(R.id.Status);
//        String thaydoitrangthai = TrangthaiListView.getText().toString();
//        if (thaydoitrangthai=="1"){
//            TrangthaiListView.setText("ON");
//        }else {
//            TrangthaiListView.setText("OFF");
//            TrangthaiListView.setTextColor(getResources().getColor(R.color.OFF));
//        }
        ListView lst = (ListView) findViewById(R.id.List);
        List<Map<String, String>> Mydatalist = null;
        ListItems Mydata = new ListItems();
        Mydatalist = Mydata.getlist();

        String[] Fromv = {"Fullname", "Phone", "Email", "Status"};
        int[] Tow = {R.id.Fullname, R.id.Phone, R.id.Email, R.id.Status};
        ad = new SimpleAdapter(Nhanvien.this, Mydatalist, R.layout.listtemplatenhanvien, Fromv, Tow);
        lst.setAdapter(ad);
    }
}