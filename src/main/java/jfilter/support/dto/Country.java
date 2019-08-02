package jfilter.support.dto;

import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 278480884092133021L;

    private Integer id;
    private String code;
    private String name;

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public Country setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Country setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }
}
