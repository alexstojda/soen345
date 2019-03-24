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
        ArrayList<String[]> value= new ArrayList<String[]>();;
        Connection conn = null;
        ResultSet result = null;
        String url = "jdbc:sqlite:petclinic.db";

        try {
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                value.add(new String[]{

                    result.getString(1),
                    result.getString(2),
                    result.getString(3)
                });
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
