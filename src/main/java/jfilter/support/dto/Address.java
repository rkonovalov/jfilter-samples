package jfilter.support.dto;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = -1818062535445307873L;

    private Integer id;
    private Country country;
    private City city;
    private Street street;

    public Address() {
        country = new Country();
        city = new City();
        street = new Street();
    }

    public Integer getId() {
        return id;
    }

    public Address setId(Integer id) {
        this.id = id;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public Address setCountry(Country country) {
        this.country = country;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Address setCity(City city) {
        this.city = city;
        return this;
    }

    public Street getStreet() {
        return street;
    }

    public Address setStreet(Street street) {
        this.street = street;
        return this;
    }
}
