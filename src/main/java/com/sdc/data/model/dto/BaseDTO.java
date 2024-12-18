package com.sdc.data.model.dto;

public class BaseDTO {

    private String id;
    private String creationDate;
    private String lastModificationDate;
    private Boolean active;

    public BaseDTO() {
        // No args constructor
    }

    public BaseDTO(String id, String creationDate, String lastModificationDate, Boolean active) {
        this.id = id;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(String lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "{\"BaseEntity\":{"
            + " \"id\":\"" + id + "\""
            + ", \"creationDate\":\"" + creationDate + "\""
            + ", \"lasModifiedDate\":\"" + lastModificationDate + "\""
            + ", \"active\":\"" + active + "\""
            + "}}";
    }
}
