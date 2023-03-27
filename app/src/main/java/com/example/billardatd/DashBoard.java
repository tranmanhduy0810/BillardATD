package com.example.billardatd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        Button btn_hoadon = (Button) findViewById(R.id.Button_Bill);

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

        btn_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,HoaDon.class);
                startActivity(intent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogLogin(Gravity.CENTER);
            }
        });

    }
    private void openDialogLogin(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        windownAttributes.gravity = gravity;
        window.setAttributes(windownAttributes);

        if (Gravity.BOTTOM == gravity){
            dialog.setCancelable(true)  ;
        }else {
            dialog.setCancelable(false);
        }

        Button buttonyes = dialog.findViewById(R.id.Button_YES);
        Button buttonno = dialog.findViewById(R.id.Button_NO);
        buttonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        buttonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(DashBoard.this, "Back To Login Success",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}