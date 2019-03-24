package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.vet.Vet;
import org.sqlite.jdbc4.JDBC4ResultSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.samples.petclinic.SQLiteConnection;

public class SQLiteVetController {

    SQLiteConnection conn = new SQLiteConnection();

    public void addAllVets(Collection<Vet> vets)  {

        //truncates table
        conn.executeSql("DELETE FROM vets");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'vets'");
        //add each owner to the table
        for (Vet vet : vets) {
            conn.executeSql("INSERT INTO vets (id, first_name, last_name)" +
                "VALUES (NULL, '" + vet.getFirstName() + "', '" + vet.getLastName() + "')");

            System.out.println(vet.getFirstName() + "', '" + vet.getLastName());
        }
        try{
            ArrayList<String[]> things = getVets();
            for (int i = 0; i < things.size(); i++) {
                System.out.println("from SQLite: " + things.get(i)[1] +" "+ things.get(i)[2]);
            }

        }catch (Exception e){
            System.out.println("getVets still doesn't work");
        }
    }

    public void addAllSpecialties(Collection<Specialty> allSpecial ){
        conn.executeSql("DELETE FROM specialties");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'specialties'");

        for (Specialty special : allSpecial){
            conn.executeSql("Insert INTO specialties (id, name)" +
                "Values (Null, '" + special.getName() +"')");
        }
    }

    public void addAllVetSpecs(List<Vet> vets) {
        //truncates table
        conn.executeSql("DELETE FROM vet_specialties;");
        //add each owner to the table
        for (Vet vet : vets) {
            List<Specialty> specs = vet.getSpecialties();
            for( Specialty temp : specs) {
                conn.executeSql("INSERT INTO vet_specialties (vet_id, specialty_id)" +
                    "VALUES ('" + vet.getId() + "', '" + temp.getId() + "')");
            }
        }
    }

    public void addVet(Vet vet){
        conn.executeSql("INSERT INTO vets (id, first_name, last_name)" +
            "VALUES (NULL, '" + vet.getFirstName() + "', '" + vet.getLastName() + "')");

        //TODO::add consistency checker here to see compare both db's
    }

    public void addSpec(Specialty spec){
        conn.executeSql("Insert INTO specialties (id, name)" +
            "Values (Null, '" + spec.getName() +"')");

        //TODO::add consistency checker here to see compare both db's
    }

    private ArrayList<String[]> getVets() {

        String sql = "Select id, first_name, last_name FROM vets";
        int numberOfFields = 3;
        return conn.getResult(sql, numberOfFields);
    }
}
