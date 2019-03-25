package org.springframework.samples.petclinic.owner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.samples.petclinic.SQLiteConnection;
import org.springframework.samples.petclinic.visit.Visit;

public class SQLiteOwnerController {

    SQLiteConnection conn = new SQLiteConnection();

//    Owners section /////////////////////////////////

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

    public void addOneOwner(Owner owner) {
        conn.executeSql("INSERT INTO owners (id, first_name, last_name, address, city, phone_number)" +
            "VALUES (NULL, '" + owner.getFirstName() + "', '" + owner.getLastName() + "', '" +
            owner.getAddress() + "', '" + owner.getCity() + "', '" + owner.getTelephone() + "')");
    }

    public void checkOwnerConsistency(Owner owner) {
        ArrayList<String[]> results = getOwner(owner.getId());
        if (results == null)
            System.out.println("Owner with id: " + owner.getId() + " was not found in SQLite DB");

        if (0 != results.get(0)[0].compareTo(owner.getId().toString()) ||
            0 != results.get(0)[1].compareTo(owner.getFirstName()) ||
            0 != results.get(0)[2].compareTo(owner.getLastName()) ||
            0 != results.get(0)[3].compareTo(owner.getAddress()) ||
            0 != results.get(0)[4].compareTo(owner.getCity()) ||
            0 != results.get(0)[5].compareTo(owner.getTelephone())) {
            System.out.println("Inconsistency found with owner id: " + results.get(0)[0] + " while adding to system");
            conn.executeSql("UPDATE owners SET first_name='" + owner.getFirstName()
                + "', last_name='" + owner.getLastName() + "', address='" + owner.getAddress() +
                "', city='" + owner.getCity() + "', phone_number='" + owner.getTelephone() + "'" +
                " WHERE id='" + owner.getId() + "'");
        }

    }


    public void checkOwnersConsistency(Collection<Owner> owners) {
        ArrayList<String[]> results = getOwners();
        int i = 0;
        int inconsitency = 0;
        for (Owner owner : owners) {
            if (0 != results.get(i)[0].compareTo(owner.getId().toString()) ||
                0 != results.get(i)[1].compareTo(owner.getFirstName()) ||
                0 != results.get(i)[2].compareTo(owner.getLastName()) ||
                0 != results.get(i)[3].compareTo(owner.getAddress()) ||
                0 != results.get(i)[4].compareTo(owner.getCity()) ||
                0 != results.get(i)[5].compareTo(owner.getTelephone())) {
                inconsitency++;
                System.out.println("Inconsistency found with owner id: " + results.get(i)[0]);
                conn.executeSql("UPDATE owners SET first_name='" + owner.getFirstName()
                    + "', last_name='" + owner.getLastName() + "', address='" + owner.getAddress() +
                    "', city='" + owner.getCity() + "', phone_number='" + owner.getTelephone() + "'" +
                    " WHERE id='" + owner.getId() + "'");
            }
            i++;
        }
        System.out.println("Number of inconsistencies in owners: " + inconsitency);
    }

    public void checkOwnersConsistencyLastName(Collection<Owner> owners, String name) {
        ArrayList<String[]> results = getOwnersByLastName(name);
        int i = 0;
        int inconsitency = 0;
        for (Owner owner : owners) {
            if (0 != results.get(i)[0].compareTo(owner.getId().toString()) ||
                0 != results.get(i)[1].compareTo(owner.getFirstName()) ||
                0 != results.get(i)[2].compareTo(owner.getLastName()) ||
                0 != results.get(i)[3].compareTo(owner.getAddress()) ||
                0 != results.get(i)[4].compareTo(owner.getCity()) ||
                0 != results.get(i)[5].compareTo(owner.getTelephone())) {
                inconsitency++;
                System.out.println("Inconsistency found with owner id: " + results.get(i)[0]);
                conn.executeSql("UPDATE owners SET first_name='" + owner.getFirstName()
                    + "', last_name='" + owner.getLastName() + "', address='" + owner.getAddress() +
                    "', city='" + owner.getCity() + "', phone_number='" + owner.getTelephone() + "'" +
                    " WHERE id='" + owner.getId() + "'");
            }
            i++;
        }
        System.out.println("Number of inconsistencies in owners while searching by last name: " + inconsitency);

    }

//    Type section //////////////////////////////

    public void addAllTypes(Collection<PetType> types) {

        //truncates table
        conn.executeSql("DELETE FROM types;");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'types'");

        //add each type to the table
        for (PetType type : types) {
            conn.executeSql("INSERT INTO types (id, name)" +
                "VALUES (NULL, '" + type.getName() + "')");
        }
    }

    //Pet section ////////////////
    public void addAllPets(Collection<Pet> pets) {

        //truncates table
        conn.executeSql("DELETE FROM pets;");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'pets'");

        //add each pet to the table
        for (Pet pet : pets) {
            conn.executeSql("INSERT INTO pets (id, name, birth_date, type_id, owner_id)" +
                "VALUES (NULL, '" + pet.getName() + "', '" + pet.getBirthDate() + "', '" +
                pet.getType().getId() + "', '" + pet.getOwner().getId() + "')");
        }
    }

    public void addOnePet(Pet pet) {
        conn.executeSql("INSERT INTO pets (id, name, birth_date, type_id, owner_id)" +
            "VALUES (NULL, '" + pet.getName() + "', '" + pet.getBirthDate() + "', '" +
            pet.getType().getId() + "', '" + pet.getOwner().getId() + "')");

    }

    public void checkPetConsistency(Pet pet) {
        ArrayList<String[]> results = getPet(pet.getId());
        if (results == null)
            System.out.println("Pet with id: " + pet.getId() + " was not found in SQLite DB");
        if (0 != results.get(0)[0].compareTo(pet.getId().toString()) ||
            0 != results.get(0)[1].compareTo(pet.getName()) ||
            0 != results.get(0)[2].compareTo(pet.getBirthDate().toString()) ||
            0 != results.get(0)[3].compareTo(pet.getType().getId().toString()) ||
            0 != results.get(0)[4].compareTo(pet.getOwner().getId().toString())) {
            System.out.println("Inconsistency found with pet id: " + results.get(0)[0] + " while adding to system");
            conn.executeSql("UPDATE pets SET name='" + pet.getName()
                + "', birth_date='" + pet.getBirthDate().toString() + "', type_id='" + pet.getType().getId() +
                "', owner_id='" + pet.getOwner().getId() + "' " +
                "' WHERE id='" + pet.getId() + "'");
        }

    }

    public void checkPetsConsistency(Collection<Pet> pets) {
        ArrayList<String[]> results = getPets();
        int i = 0;
        int inconsitency = 0;
        for (Pet pet : pets) {
            if (0 != results.get(i)[0].compareTo(pet.getId().toString()) ||
                0 != results.get(i)[1].compareTo(pet.getName()) ||
                0 != results.get(i)[2].compareTo(pet.getBirthDate().toString()) ||
                0 != results.get(i)[3].compareTo(pet.getType().getId().toString()) ||
                0 != results.get(i)[4].compareTo(pet.getOwner().getId().toString())) {
                System.out.println("Inconsistency found with pet id: " + results.get(i)[0]);
                conn.executeSql("UPDATE pets SET name='" + pet.getName()
                    + "', birth_date='" + pet.getBirthDate().toString() + "', type_id='" + pet.getType().getId() +
                    "', owner_id='" + pet.getOwner().getId() + "' " +
                    "WHERE id='" + pet.getId() + "'");
                inconsitency++;
            }
            i++;
        }
        System.out.println("Number of inconsistencies in pets: " + inconsitency);
    }

//    Visits section ///////////////

    public void addAllVisits(List<Visit> visits) {

        //truncates table
        conn.executeSql("DELETE FROM visits;");
        conn.executeSql("DELETE FROM sqlite_sequence WHERE name = 'visits'");

        //add each visit to the table
        for (Visit visit : visits) {
            conn.executeSql("INSERT INTO visits (id, pet_id, visit_date, description)" +
                "VALUES (NULL, '" + visit.getPetId() + "', '" + visit.getDate() + "', '" +
                visit.getDescription() + "')");
        }

    }

    public void addOneVisit(Visit visit) {
        conn.executeSql("INSERT INTO visits (id, pet_id, visit_date, description)" +
            "VALUES (NULL, '" + visit.getPetId() + "', '" + visit.getDate() + "', '" +
            visit.getDescription() + "')");
    }

    public void checkVisitConsistency(Visit visit) {
        ArrayList<String[]> results = getVisit(visit.getId());
        if (results == null)
            System.out.println("Visit with id: " + visit.getId() + " was not found in SQLite DB");

        if (0 != results.get(0)[0].compareTo(visit.getId().toString()) ||
            0 != results.get(0)[1].compareTo(visit.getPetId().toString()) ||
            0 != results.get(0)[2].compareTo(visit.getDate().toString()) ||
            0 != results.get(0)[3].compareTo(visit.getDescription())) {
            System.out.println("Inconsistency found with visit id: " + results.get(0)[0] + " while adding to system");
            conn.executeSql("UPDATE visits SET pet_id='" + visit.getPetId()
                + "', visit_date='" + visit.getDate().toString() + "', description='" + visit.getDescription() +
                " ' WHERE id='" + visit.getId() + "'");
        }

    }

    public void checkVisitsConsistency(Collection<Visit> visits) {
        ArrayList<String[]> results = getVisits();
        int i = 0;
        int inconsitency = 0;
        for (Visit visit : visits) {
            if (0 != results.get(i)[0].compareTo(visit.getId().toString()) ||
                0 != results.get(i)[1].compareTo(visit.getPetId().toString()) ||
                0 != results.get(i)[2].compareTo(visit.getDate().toString()) ||
                0 != results.get(i)[3].compareTo(visit.getDescription())) {
                System.out.println("Inconsistency found with visit id: " + results.get(i)[0]);
                conn.executeSql("UPDATE visits SET pet_id='" + visit.getPetId()
                    + "', visit_date='" + visit.getDate().toString() + "', description='" + visit.getDescription() +
                    "' WHERE id='" + results.get(i)[0] + "'");
                inconsitency++;
            }
            i++;
        }
        System.out.println("Number of inconsistencies in visits: " + inconsitency);
    }

    // getters///////////////////
    public ArrayList<String[]> getTypes() {

        String sql = "Select id, name FROM types";
        int numberOfFields = 2;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getPets() {

        String sql = "Select id, name, birth_date, type_id, owner_id FROM pets";
        int numberOfFields = 5;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getVisits() {

        String sql = "Select id, pet_id, visit_date, description FROM visits";
        int numberOfFields = 4;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getOwners() {
        String sql = "Select id, first_name, last_name, address, city, phone_number FROM owners";
        int numberOfFields = 6;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getOwnersByLastName(String name) {
        String sql = "Select id, first_name, last_name, address, city, phone_number FROM owners WHERE last_name LIKE '" + name + "'";
        int numberOfFields = 6;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getType(int id) {

        String sql = "Select id, name FROM types WHERE id=" + id;
        int numberOfFields = 2;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getPet(int id) {

        String sql = "Select id, name, birth_date, type_id, owner_id FROM pets WHERE id=" + id;
        int numberOfFields = 5;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getVisit(int id) {

        String sql = "Select id, pet_id, visit_date, description FROM visits WHERE id=" + id;
        int numberOfFields = 4;
        return conn.getResult(sql, numberOfFields);
    }

    public ArrayList<String[]> getOwner(int id) {
        String sql = "Select id, first_name, last_name, address, city, phone_number FROM owners WHERE id=" + id;
        int numberOfFields = 6;
        return conn.getResult(sql, numberOfFields);
    }

    public void updateOwner(Owner owner) {
        conn.executeSql("UPDATE owners (id, first_name, last_name, address, city, phone_number)" +
            "VALUES ("+owner.getId()+", '" + owner.getFirstName() + "', '" + owner.getLastName() + "', '" +
            owner.getAddress() + "', '" + owner.getCity() + "', '" + owner.getTelephone() + "')");

    }

    public void updatePet(Pet pet) {
        conn.executeSql("UPDATE INTO pets (id, name, birth_date, type_id, owner_id)" +
            "VALUES ("+pet.getId()+", '" + pet.getName() + "', '" + pet.getBirthDate() + "', '" +
            pet.getType().getId() + "', '" + pet.getOwner().getId() + "')");
    }
}
