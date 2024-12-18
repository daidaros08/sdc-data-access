package com.sdc.data.model;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.sdc.data.model.Builder.DATE;
import static com.sdc.data.model.Builder.DESCRIPTION;
import static com.sdc.data.model.Builder.ID;
import static com.sdc.data.model.Builder.NAME;
import static com.sdc.data.model.Builder.buildEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityTest {

    protected static Validator validator;


    @BeforeEach
    public void setUp()  {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldBuildEntity() {
        SampleEntity entity = buildEntity();

        assertEquals(entity.getId(), ID);
        assertEquals(entity.getCreationDate(), DATE);
        assertEquals(entity.getActive(), Boolean.TRUE);
        assertEquals(entity.getName(), NAME);
        assertEquals(entity.getDescription(), DESCRIPTION);
    }

    @Test
    public void shouldThrowValidationEntityError() {
        SampleEntity entity = new SampleEntity();
        Set<ConstraintViolation<SampleEntity>> violations = validator.validate(entity);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldValidateEntity() {
        SampleEntity entity = buildEntity();
        Set<ConstraintViolation<SampleEntity>> violations = validator.validate(entity);

        assertTrue(violations.isEmpty());
    }
}
