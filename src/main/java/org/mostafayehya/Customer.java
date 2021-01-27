package org.mostafayehya;

public class Customer {

    public int customer_id;
    public String firstName;
    public String lastName;
    public String email;

    public Customer() {

    }

    public Customer(int id, String first, String last, String email) {
        this.customer_id = id;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
    }

}
