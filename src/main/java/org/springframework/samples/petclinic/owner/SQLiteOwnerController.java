package org.springframework.samples.petclinic.owner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class SQLiteOwnerController {

    public void addAllOwners(Collection<Owner> owners) {
        //truncates table
        executeSql("DELETE FROM owners;");
        //add each owner to the table
        for (Owner owner : owners) {
            executeSql("INSERT INTO owners (id, first_name, last_name, address, city, phone_number)" +
                "VALUES (NULL, '" + owner.getFirstName() + "', '" + owner.getLastName() + "', '" +
                owner.getAddress() + "', '" + owner.getCity() + "', '" + owner.getTelephone() + "')");
        }
    }

    private void executeSql(String sql) {
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
}
