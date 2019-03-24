package org.springframework.samples.petclinic;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteConnection {

    public void executeSql(String sql) {
        Connection conn = null;

        String url = "jdbc:sqlite:petclinic.db";

        try {
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<String[]>  getResult(String sql, int fields) {
        ArrayList<String[]> value= new ArrayList<String[]>();
        Connection conn = null;
        ResultSet result = null;
        String url = "jdbc:sqlite:petclinic.db";

        try {
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){

                String[] rowData = new String[fields];
                for(int i = 0; i< fields; i++){
                    rowData[i] = result.getString(i+1);
                }
                value.add(rowData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                return value;
            }
        }
    }
}
