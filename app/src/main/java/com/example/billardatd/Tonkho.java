package com.example.billardatd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tonkho extends AppCompatActivity {
    Connection connect;

    SimpleAdapter adapter;
    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tonkho);

        ImageView backtonkho = (ImageView) findViewById(R.id.Back_TonKho);
        backtonkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Tonkho.super.onBackPressed();
                Intent intent = new Intent(Tonkho.this,DashBoard.class);
                startActivity(intent);
                finish();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Tonkho.this,"Back Success",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ListView ls_TonKho = (ListView) findViewById(R.id.LV_TonKho);
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if (connect != null) {
                String query = "select * from Food";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Map<String, String> tab = new HashMap<String, String>();
                    tab.put("TenMon", rs.getString("name"));
                    tab.put("SoLuong", rs.getString("salary"));
                    tab.put("Price", rs.getString("price"));
                    tab.put("NgayNhap", rs.getString("timeIn"));
                    data.add(tab);
                }
                String[] from = {"TenMon", "SoLuong", "Price", "NgayNhap"};
                int[] to = {R.id.TenMon, R.id.SoLuong, R.id.Price, R.id.NgayNhap};

                adapter = new SimpleAdapter(Tonkho.this,data,R.layout.listtemplatetonkho, from,to);
                ls_TonKho.setAdapter(adapter);
            } else {
                ConnectionResult = "Check Connection";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}