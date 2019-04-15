package org.springframework.samples.petclinic.vet;

public class VetPayment {
    private String fname;
    private String lname;
    private String address;
    private double amount;
    private String vetName;

//    public VetPayment(String fname, String lname, String address, double amount, String vetName) {
//        this.fname = fname;
//        this.lname = lname;
//        this.address = address;
//        this.amount = amount;
//        this.vetName = vetName;
//    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

}
