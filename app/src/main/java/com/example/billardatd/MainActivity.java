package com.example.billardatd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connect;
    String ConnectionResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        EditText Ed_User = (EditText) findViewById(R.id.Edit_Text_User);
        EditText Ed_Pass = (EditText) findViewById(R.id.Edit_Text_Password);
        Button Bt_Login = (Button) findViewById(R.id.Button_Login);

        Bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();
                    if(connect!=null){
                        String query = "SELECT * FROM dbo.Account WHERE email = '" + Ed_User.getText() + "' AND PassWord = '" + Ed_Pass.getText() + "' ";
                        Statement st= connect.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        if (rs.next()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                                }
                            });
                            ConnectionResult = "Success";
                            Intent intent = new Intent(MainActivity.this,DashBoard.class);
                            startActivity(intent);
                            finish();
                    } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"Check username or password",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}