package org.springframework.samples.petclinic.owner;

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
}
