package com.example.billardatd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoaDon extends AppCompatActivity{
    private TextView dateInButton;
    Connection connect;

    SimpleAdapter adapter;
    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        int count = 0;
        Button Buttonshow = (Button) findViewById(R.id.Button_Show);
        ListView lv_Hoadon = (ListView) findViewById(R.id.Lv_Hoadon);
        ImageView button_backhoadon = (ImageView) findViewById(R.id.Back_HoaDon);
        TextView Tonghoadon = (TextView) findViewById(R.id.TongHoaDon);
        EditText Chonngay = (EditText) findViewById(R.id.ChonNgay);

        button_backhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaDon.super.onBackPressed();
                Intent intent = new Intent(HoaDon.this, DashBoard.class);
                startActivity(intent);
                finish();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HoaDon.this, "Back Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if (connect != null) {
                        String query = "select * from bill";
                        Statement st = connect.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        while (rs.next()) {
                            Map<String, String> tab = new HashMap<String, String>();
                            tab.put("Tenban", rs.getString("idTable"));
                            tab.put("DateIn", rs.getString("DateCheckIn"));
                            tab.put("Price", rs.getString("totalPrice"));
                            tab.put("DateOut", rs.getString("DateCheckOut"));
                            tab.put("CaTruc", rs.getString("idAccount"));
                            data.add(tab);
                        }
                        String[] from = {"Tenban", "DateIn", "Price", "DateOut","CaTruc"};
                        int[] to = {R.id.Tenban, R.id.DateIn, R.id.Price, R.id.DateOut,R.id.CaTruc};

                        adapter = new SimpleAdapter(HoaDon.this,data,R.layout.listtemplatehgoadon, from,to);
                        lv_Hoadon.setAdapter(adapter);
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
