package com.example.billardatd;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.anychart.charts.Pie;
import com.anychart.core.annotations.Line;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class DoanhThu extends AppCompatActivity {
    BarChart bar;
    Connection connect;
    Boolean isSucess = false;
    String ConnectionResult="";
    final String[] days = {"DD/MM/YYYY"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        ImageView buttonback = (ImageView) findViewById(R.id.Back_Button_DoanhThu);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {DoanhThu.super.onBackPressed();
                Intent intent = new Intent(DoanhThu.this,DashBoard.class);
                startActivity(intent);
                finish();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DoanhThu.this,"Back Success",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if (connect!=null) {
                String query = "select DateCheckOut,totalPrice, discount from Bill";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                List<BarEntry> priceEntries = new ArrayList<>();
                List<BarEntry> discountEntries = new ArrayList<>();
                List<Entry> lineEntries = new ArrayList<>();

                while (rs.next()) {
                    Date date = rs.getDate("DateCheckOut");
                    float totalPrice = rs.getFloat("totalPrice");
                    int discount = rs.getInt("discount");
                    priceEntries.add(new BarEntry(priceEntries.size(), totalPrice));
                    discountEntries.add(new BarEntry(discountEntries.size(), discount));
                    lineEntries.add(new Entry(lineEntries.size(), totalPrice, date));

                }
                BarDataSet priceDataSet = new BarDataSet(priceEntries, "Doanh thu");
                priceDataSet.setColor(Color.BLUE);
                BarDataSet discountDataSet = new BarDataSet(discountEntries, "Giảm giá");
                discountDataSet.setColor(Color.RED);
                BarData barData = new BarData(priceDataSet, discountDataSet);
                LineDataSet lineDataSet = new LineDataSet(lineEntries, "Đường doanh thu");
                lineDataSet.setColor(Color.GREEN);
                LineData lineData = new LineData(lineDataSet);
                BarChart barChart = findViewById(R.id.BarChart);
                barChart.setData(barData);
                // customize the bar chart as desired
                barChart.getDescription().setText("Doanh thu và Giảm giá");
                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(days));
                barChart.getXAxis().setGranularity(1f);
                barChart.groupBars(0f, 0.1f, 0.05f);
                LineChart lineChart = findViewById(R.id.LineChart);
                lineChart.setData(lineData);
                lineChart.getDescription().setText("MIN");
                lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(days));
                lineChart.getXAxis().setGranularity(1f);

// set the same X-axis range for both charts
                barChart.setVisibleXRangeMaximum(5f);
                lineChart.setVisibleXRangeMaximum(5f);

// close the database connection
                connect.close();

            }
        }catch (Exception e){

        }
    }}
