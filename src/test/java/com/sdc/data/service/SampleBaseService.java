package com.sdc.data.service;

import javax.validation.Validation;

import org.springframework.stereotype.Component;
import com.sdc.data.model.SampleEntity;
import com.sdc.data.repository.SampleRepository;

@Component
public class SampleBaseService extends BaseService <SampleEntity, SampleRepository> {

    public SampleBaseService(SampleRepository repository) {
        super(SampleEntity.class, repository);
        super.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
