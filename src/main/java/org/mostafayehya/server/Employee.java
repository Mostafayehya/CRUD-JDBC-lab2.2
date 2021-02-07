package org.mostafayehya.server;

import java.io.Serializable;

public class Employee implements Serializable {

    public int employee_id;
    public String firstName;
    public String lastName;
    public String sex;
    public int age;
    public String address;
    public int phoneNumber;
    public int vacationBalance;


    public Employee() {

    }

    public Employee(int id, String first, String last,String sex,int age, String address,int phoneNumber,int vacationBalance) {
        this.employee_id = id;
        this.firstName = first;
        this.lastName = last;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.phoneNumber=phoneNumber;
        this.vacationBalance = vacationBalance;
    }

}
