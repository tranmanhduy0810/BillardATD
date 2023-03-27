package com.example.billardatd;

import static android.util.Log.println;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Nhanvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);

    }

    SimpleAdapter ad;

    public void GetList(View v) {
        ListView lst = (ListView) findViewById(R.id.ListView);
        Button BTNOw = (Button) findViewById(R.id.Click);
//        TextView status = (TextView) findViewById(R.id.Status);
//        String pin = status.getText().toString();
//        System.out.println(pin);
        List<Map<String, String>> Mydatalist = null;
        ListItems Mydata = new ListItems();
        Mydatalist = Mydata.getlist();

        String[] Fromv = {"Fullname", "Phone", "Email", "Status"};
        int[] Tow = {R.id.Fullname, R.id.Phone, R.id.Email, R.id.Status};
        ad = new SimpleAdapter(Nhanvien.this, Mydatalist, R.layout.listtemplatenhanvien, Fromv, Tow);
        lst.setAdapter(ad);
    }
}