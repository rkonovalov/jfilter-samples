package jfilter.support.dto;

import java.io.Serializable;

public class City implements Serializable {
    private static final long serialVersionUID = -6697684889549787300L;

    private Integer id;
    private String name;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public City setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }
}
