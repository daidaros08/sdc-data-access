package com.sdc.data.resource;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseResource {
    protected static final String OFFSET = "offset";
    protected static final String LIMIT = "limit";
    protected static final String SORT = "sort";
    protected static final String ORDER = "order";
    protected static final String ID = "id";
    private final static Logger LOG = LoggerFactory.getLogger(BaseResource.class);

    @Autowired
    private Validator validator;



    public BaseResource() {
        // No args constructor
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    protected void validateRequest (@NotNull Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = this.validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }


}
