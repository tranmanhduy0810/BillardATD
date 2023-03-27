package com.example.billardatd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItems {
    Connection connect;
    String ConnectionResult="";
    Boolean isSucess = false;

    public List<Map<String,String>> getlist(){
        List<Map<String,String>> data = null;

        data= new ArrayList<Map<String,String>>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if (connect!=null){
                String query1 = "select status from Account";
                String query = "Select * from Account";
                Statement st= connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next())
                {
                    Map<String,String> dtname = new HashMap<String,String>();
                    dtname.put("Fullname",rs.getString("name"));
                    dtname.put("Phone",rs.getString("phone"));
                    dtname.put("Status", rs.getString("status"));
                    dtname.put("Email",rs.getString("email"));
                    data.add(dtname);
                }
                ConnectionResult="Success";
                isSucess=true;
                connect.close();
            }
            else {
                ConnectionResult ="Failed";
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
