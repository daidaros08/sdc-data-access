package com.sdc.data.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class Builder {

    public static final String ID = "123";
    public static final String NAME = "name";
    public static final String NEW_NAME = "newName";
    public static final String DESCRIPTION = "description";
    public static final Date DATE = new Date();

    public static SampleEntity buildEntity() {
        SampleEntity entity = new SampleEntity();
        entity.setId(ID);
        entity.setCreationDate(DATE);
        entity.setActive(Boolean.TRUE);
        entity.setName(NAME);
        entity.setDescription(DESCRIPTION);
        return entity;
    }

    public static SampleEntity buildEntityUpdate() {
        SampleEntity entity = buildEntity();
        entity.setName(NEW_NAME);
        return entity;
    }

    public static Page<SampleEntity> buildPage(List<SampleEntity> entities) {
        return new PageImpl<>(entities);
    }
}
