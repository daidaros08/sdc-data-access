package com.sdc.data.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import static com.sdc.data.utils.BaseConstants.DEFAULT_DATE_FORMAT;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity implements Serializable {
    @Id
    @Schema(readOnly = true)
    @Field()
    @Indexed(unique = true)
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DEFAULT_DATE_FORMAT)
    @Field("createdDate")
    @Schema(readOnly = true)
    @CreatedDate
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DEFAULT_DATE_FORMAT)
    @Field("lastModifiedDate")
    @Schema(readOnly = true)
    @LastModifiedDate
    private Date lastModifiedDate;

    @Field("active")
    @Schema(required = true)
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreationDate(Date date) {
        this.createdDate = date;
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
            + ", \"createdDate\":\"" + createdDate + "\""
            + ", \"lasModifiedDate\":\"" + lastModifiedDate + "\""
            + ", \"active\":\"" + active + "\""
            + "}}";
    }
}
