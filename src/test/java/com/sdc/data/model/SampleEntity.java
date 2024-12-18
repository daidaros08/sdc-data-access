package com.sdc.data.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;


public class SampleEntity extends BaseEntity {

    @Indexed(unique = true)
    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "description cannot be null")
    private String description;

    public SampleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
