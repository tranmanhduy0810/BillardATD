package com.example.billardatd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Types;

public class Banchoi extends AppCompatActivity {
    Connection connect;

    SimpleAdapter adapter;
    String ConnectionResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banchoi);
        ImageView backbanchoi = (ImageView) findViewById(R.id.Back_BanChoi);
        backbanchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Banchoi.super.onBackPressed();
                Intent intent = new Intent(Banchoi.this,DashBoard.class);
                startActivity(intent);
                finish();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Banchoi.this,"Back Success",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        EditText Ed_Table = (EditText) findViewById(R.id.Edittext_SearchBanChoi);
        ImageView searchTable = (ImageView) findViewById(R.id.Button123);
        ImageView findallTable = (ImageView) findViewById(R.id.Img_findTable);
        TextView Tx_Tenban = (TextView) findViewById(R.id.TextView_tenban);
        TextView Tx_LoaiBan = (TextView) findViewById(R.id.Textview_loaiban);
        TextView Tx_Trangthaiban = (TextView) findViewById(R.id.Tx_StatusTable);
        ListView listTable = (ListView) findViewById(R.id.List_Table);
        View inflatedView = getLayoutInflater().inflate(R.layout.listtemplatetable, null);
        ImageView batden = (ImageView) inflatedView.findViewById(R.id.Name_Table1);
        TextView statustable = (TextView) inflatedView.findViewById(R.id.Status_Table);


        searchTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if (connect!=null){
                        String query = "select * from TableBida where name like '%"+Ed_Table.getText().toString()+"%'";
                        Statement st= connect.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        while (rs.next())
                        {
                            Tx_Tenban.setText(rs.getString(2));
                            Tx_Tenban.setTextColor(getResources().getColor(R.color.NAME));
                            //Tx_gmail.setText(rs.getString(2));
                            Tx_LoaiBan.setText(rs.getString(3));
                            Tx_Trangthaiban.setText(rs.getString(4));
                            String thaydoi = Tx_Trangthaiban.getText().toString();
                            String thaydoiden = statustable.getText().toString().trim();
                            if (thaydoiden.equals("None")){
                                batden.setImageResource(R.drawable.tableoff);
                            }

                            if (thaydoi.equals("Active")){
                                Tx_Trangthaiban.setTextColor(getResources().getColor(R.color.ON));
                            }
                            else{
                                Tx_Trangthaiban.setTextColor(getResources().getColor(R.color.OFF));
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

        findallTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if (connect != null) {
                        String query = "select * from TableBida";
                        Statement st = connect.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        while (rs.next()) {
                            Map<String, String> tab = new HashMap<String, String>();
                            tab.put("Name_Table", rs.getString("name"));
                            tab.put("ID_Table", rs.getString("id"));
                            tab.put("LoaiBan", rs.getString("classification"));
                            tab.put("Status_Table", rs.getString("status"));
                            data.add(tab);
                        }
                        String[] from = {"Name_Table", "ID_Table", "LoaiBan", "Status_Table"};
                        int[] to = {R.id.Name_Table, R.id.ID_Table, R.id.LoaiBan, R.id.Status_Table};

                        adapter = new SimpleAdapter(Banchoi.this,data,R.layout.listtemplatetable, from,to);
                        listTable.setAdapter(adapter);
                    } else {
                        ConnectionResult = "Check Connection";
                    }
                } catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }



        });
    }}







