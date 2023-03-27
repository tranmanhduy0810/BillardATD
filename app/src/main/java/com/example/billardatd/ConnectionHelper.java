package com.example.billardatd;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database,ipmain;
    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
    ip = "199.102.48.244";
    database= "db_a97447_billard";
    uname="db_a97447_billard_admin";
    pass="@Manhduy0810";
    port="1433";
    for (int i=0;i<=255;i++){
         ipmain = ip+i;
    }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL= null;
        String query = "SELECT SERVERPROPERTY('ComputerNamePhysicalNetBIOS') [Machine Name]\n" +
                "   ,CLIENT_NET_ADDRESS AS [IP Address Of Client]\n" +
                " FROM SYS.DM_EXEC_CONNECTIONS \n" +
                " WHERE SESSION_ID = @@SPID";
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);



        }
        catch (Exception ex)
        {
            Log.e("Error",ex.getMessage());
        }
        return connection;
    }
}

