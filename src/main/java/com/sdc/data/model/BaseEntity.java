package com.sdc.data.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import static com.sdc.data.utils.BaseConstants.DEFAULT_DATE_FORMAT;

public abstract class BaseEntity implements Serializable {
    @Id
    @ApiModelProperty(readOnly = true)
    @Field("id")
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DEFAULT_DATE_FORMAT)
    @Field("creationDate")
    @ApiModelProperty(readOnly = true)
    private Date creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DEFAULT_DATE_FORMAT)
    @Field("lastModifiedDate")
    @ApiModelProperty(readOnly = true)
    private Date lastModifiedDate;

    @Field("active")
    @ApiModelProperty(required = true)
    protected Boolean active = true;

    public BaseEntity() {
        // No args constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String toString() {
        return "{\"BaseEntity\":{"
            + " \"id\":\"" + id + "\""
            + ", \"creationDate\":\"" + creationDate + "\""
            + ", \"lasModifiedDate\":\"" + lastModifiedDate + "\""
            + ", \"active\":\"" + active + "\""
            + "}}";
    }
}
