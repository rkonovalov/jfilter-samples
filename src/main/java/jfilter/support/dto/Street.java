package jfilter.support.dto;

import java.io.Serializable;

public class Street implements Serializable {
    private static final long serialVersionUID = 8881615336689065327L;

    private Integer id;
    private String streetName;
    private Integer streetNumber;

    public Street() {
    }

    public Integer getId() {
        return id;
    }

    public Street setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public Street setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public Street setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }
}
