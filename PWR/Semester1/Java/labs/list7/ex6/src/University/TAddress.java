package University;

public class TAddress {
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;

    public TAddress(String country, String city, String street, String houseNumber, String flatNumber){
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    void setCountry(String country){
        this.country = country;
    }

    void setCity(String city){
        this.city = city;
    }

    void setStreet(String street){
        this.street = street;
    }

    void setHouseNumber(String houseNumber){
        this.houseNumber = houseNumber;
    }

    void setFlatNumber(String flatNumber){
        this.flatNumber = flatNumber;
    }

    String getCountry(){
        return this.country;
    }

    String getCity(){
        return this.city;
    }

    String getStreet(){
        return street;
    }

    String getHouseNumber(){
        return this.houseNumber;
    }

    String getFlatNumber(){
        return this.flatNumber;
    }

    void printAddress() {
        System.out.println("Address: "+street + " " + houseNumber + "/" + flatNumber + ", " + city + ", " + country);
    }
}