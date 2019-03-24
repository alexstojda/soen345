package org.springframework.samples.petclinic.owner;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.samples.petclinic.SQLiteConnection;

public class SQLiteOwnerController {

    SQLiteConnection conn = new SQLiteConnection();

    public void addAllOwners(Collection<Owner> owners) {

        //truncates table
        conn.executeSql("DELETE FROM owners;");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'owners'");
        //add each owner to the table
        for (Owner owner : owners) {
            conn.executeSql("INSERT INTO owners (id, first_name, last_name, address, city, phone_number)" +
                "VALUES (NULL, '" + owner.getFirstName() + "', '" + owner.getLastName() + "', '" +
                owner.getAddress() + "', '" + owner.getCity() + "', '" + owner.getTelephone() + "')");
        }
    }

    public void addAllTypes(Collection<PetType> types) {

        //truncates table
        conn.executeSql("DELETE FROM types;");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'types'");
        //add each type to the table
        for (PetType type : types) {
            conn.executeSql("INSERT INTO types (id, name)" +
                "VALUES (NULL, '" + type.getName() + "')");

            System.out.println(type.getName());
        }
        try{
            ArrayList<String[]> sqliteTypes = getTypes();
            for (int i = 0; i < sqliteTypes.size(); i++) {
                System.out.println("from SQLite: " + sqliteTypes.get(i)[1]);
            }

        }catch (Exception e){
            System.out.println("There was an error with getTypes");
        }
    }

    public void addAllPets(Collection<Pet> pets) {

        //truncates table
        conn.executeSql("DELETE FROM pets;");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'pets'");
        //add each pet to the table
        for (Pet pet : pets) {
            conn.executeSql("INSERT INTO pets (id, name, birth_date, type_id, owner_id)" +
                "VALUES (NULL, '" + pet.getName() + "', '" + pet.getBirthDate() + "', '" +
                pet.getType().getId() + "', '" + pet.getOwner().getId() + "')");


            System.out.println(pet.getName() + "', '" + pet.getBirthDate() + "', '" + pet.getType().getId()
                + "', '" + pet.getOwner().getId());
        }
        try{
            ArrayList<String[]> sqlitePets = getPets();
            for (int i = 0; i < sqlitePets.size(); i++) {
                System.out.println("from SQLite: " + sqlitePets.get(i)[1] + "', '" + sqlitePets.get(i)[2] + "', '" +
                    sqlitePets.get(i)[3] + "', '" + sqlitePets.get(i)[4]);
            }

        }catch (Exception e){
            System.out.println("There was an error with getPets");
        }
    }

    private ArrayList<String[]> getTypes() {

        String sql = "Select id, name FROM types";
        int numberOfFields = 2;
        return conn.getResult(sql, numberOfFields);
    }

    private ArrayList<String[]> getPets() {

        String sql = "Select id, name, birth_date, type_id, owner_id FROM pets";
        int numberOfFields = 5;
        return conn.getResult(sql, numberOfFields);
    }
}
