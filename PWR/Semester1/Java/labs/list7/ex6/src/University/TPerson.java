package University;

import static java.lang.IO.println;

public class TPerson {
    private String personID;
    private String name;
    private String surname;
    private int age;
    private int weight;
    private TSex sex;
    private TAddress homeAddress;

    public TPerson(String personID, String name, String surname, int age, int weight, TSex sex, TAddress homeAddress){
        this.personID = personID;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        this.weight = weight;
        this.homeAddress = homeAddress;
    }

    String getPersonID() {
        return personID;
    }

    void setPersonID(String personID) {
        this.personID = personID;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    TSex getSex() {
        return sex;
    }

    void setSex(TSex sex) {
        this.sex = sex;
    }

    TAddress getHomeAddress() {
        return homeAddress;
    }

    void setHomeAddress(TAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    void printPerson() {
        println(name + " " + surname + " (" + sex + "), age: " + age + ", weight: " + weight);
        homeAddress.printAddress();
    }
}