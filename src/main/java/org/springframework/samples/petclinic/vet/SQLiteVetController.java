package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.vet.Vet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

public class SQLiteVetController {

    public void addAllVets(Collection<Vet> vets) {
        //truncates table
        executeSql("DELETE FROM vets;");
        //add each owner to the table
        for (Vet vet : vets) {
            executeSql("INSERT INTO vets (id, first_name, last_name)" +
                "VALUES (NULL, '" + vet.getFirstName() + "', '" + vet.getLastName() + "')");
        }
    }

    public void addAllSpecialties(Collection<Specialty> allSpecial ){
        executeSql("DELETE FROM specialties");

        for (Specialty special : allSpecial){
            executeSql("Insert INTO specialties (id, name)" +
                "Values (Null, '" + special.getName() +"')");
        }
    }

    public void addAllVetSpecs(List<Vet> vets, Collection<Specialty> allSpecial) {
        //truncates table
        executeSql("DELETE FROM vet_specialties;");
        //add each owner to the table
        for (Vet vet : vets) {
            List<Specialty> specs = vet.getSpecialties();
            for( Specialty temp : specs) {
                executeSql("INSERT INTO vet_specialties (vet_id, specialty_id)" +
                    "VALUES ('" + vet.getId() + "', '" + temp.getId() + "')");
            }
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
