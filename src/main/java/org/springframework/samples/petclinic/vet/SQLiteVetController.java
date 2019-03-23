package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.vet.Vet;
import org.sqlite.jdbc4.JDBC4ResultSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLiteVetController {

    public void addAllVets(Collection<Vet> vets)  {
        //truncates table
        executeSql("DELETE FROM vets");
        executeSql("DELETE FROM sqlite_sequence WHERE name = 'vets'");
        //add each owner to the table
        for (Vet vet : vets) {
            executeSql("INSERT INTO vets (id, first_name, last_name)" +
                "VALUES (NULL, '" + vet.getFirstName() + "', '" + vet.getLastName() + "')");
        }
        try{
            ArrayList<String[]> things = getVets();
            System.out.println("Done");
        }catch (Exception e){
            System.out.println("getVets still doens't work");
        }



    }

    public void addAllSpecialties(Collection<Specialty> allSpecial ){
        executeSql("DELETE FROM specialties");
        executeSql("DELETE FROM sqlite_sequence WHERE name = 'specialties'");

        for (Specialty special : allSpecial){
            executeSql("Insert INTO specialties (id, name)" +
                "Values (Null, '" + special.getName() +"')");
        }
    }

    public void addAllVetSpecs(List<Vet> vets) {
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

    public void addVet(Vet vet){
        executeSql("INSERT INTO vets (id, first_name, last_name)" +
            "VALUES (NULL, '" + vet.getFirstName() + "', '" + vet.getLastName() + "')");

        //TODO::add consistency checker here to see compare both db's
    }

    public void addSpec(Specialty spec){
        executeSql("Insert INTO specialties (id, name)" +
            "Values (Null, '" + spec.getName() +"')");

        //TODO::add consistency checker here to see compare both db's
    }

    private void executeSql(String sql) {
        Connection conn = null;
//        ResultSet result = null;
        String url = "jdbc:sqlite:petclinic.db";

        try {
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
           statement.execute(sql);
//           result = statement.executeQuery(sql);
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

    private ArrayList<String[]> getVets() {
        ArrayList<String[]> value= new ArrayList<String[]>();;
        String sql = "Select id, first_name, last_name FROM vets";
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
