package com.sdc.data.service;

import javax.validation.Validation;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.sdc.data.model.SampleEntity;
import com.sdc.data.repository.SampleRepository;

@Component
public class SampleBaseService extends BaseService <SampleEntity, SampleRepository> {

    public SampleBaseService(SampleRepository repository) {
        super(SampleEntity.class, repository);
        super.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
