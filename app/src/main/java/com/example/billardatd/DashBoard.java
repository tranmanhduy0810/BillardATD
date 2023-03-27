package com.example.billardatd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();
        ImageView btn_back = (ImageView) findViewById(R.id.Back_Button);
        Button btn_doanhthu = (Button) findViewById(R.id.Button_Revenue);
        Button btn_nhanvien = (Button) findViewById(R.id.Button_NhanVien);
        Button btn_banchoi = (Button) findViewById(R.id.Button_BanHoatDong);
        Button btn_tonkho = (Button) findViewById(R.id.Button_TonKho);

        btn_nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,Nhanvien.class);
                startActivity(intent);
                finish();
            }
        });

        btn_banchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,Banchoi.class);
                startActivity(intent);
                finish();
            }
        });

        btn_tonkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,Tonkho.class);
                startActivity(intent);
                finish();
            }
        });

        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,DoanhThu.class);
                startActivity(intent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashBoard.super.onBackPressed();
                Intent intent = new Intent(DashBoard.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}