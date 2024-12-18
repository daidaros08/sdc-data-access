package com.sdc.data.service;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sdc.data.exception.BadRequestException;
import com.sdc.data.exception.EntityAlreadyExistException;
import com.sdc.data.exception.EntityNotFoundException;
import com.sdc.data.model.BaseEntity;
import com.sdc.data.repository.BaseRepository;

@Service
public class BaseService <T extends BaseEntity, R extends BaseRepository<T, String>> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);
    private static final String ENTITY_NOT_FOUND = "service.generic.entity.not-found";
    private static final String ENTITIES_NOT_FOUND = "service.generic.entities.not-found";
    private static final String ENTITY_EXIST_EXCEPTION = "service.generic.entity-exist.exception";

    @Autowired
    protected Validator validator;

    protected final R repository;

    protected Class<T> type;

    public BaseService(Class<T> type, R repository) {
        this.repository = repository;
        this.type = type;
    }

    public Page<T> getAll(Pageable pageable) {
        try {
            Page<T> entities = repository.findByActiveTrue(pageable);
            return entities;
        } catch (Exception ex) {
            LOG.error("Unexpected error while fetching entities", ex);
            throw new BadRequestException("Error fetching entities", ex.getMessage(), this.type);
        }
    }

    public T getById(String id) {
        try {
            return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found", ex);
            throw new EntityNotFoundException(ENTITY_NOT_FOUND, ex.getErrorCode(), this.type);
        } catch (Exception ex) {
            LOG.error("Unexpected error while fetching entity by ID", ex);
            throw new BadRequestException("Error fetching entity by ID", ex.getMessage(), this.type);
        }
    }

    public T create(T entity) {
        try {
            validateEntity(entity);
            if (repository.existsById(entity.getId())) {
                throw new EntityAlreadyExistException(ENTITY_EXIST_EXCEPTION, entity.getClass());
            }
            return repository.insert(entity);
        } catch (ConstraintViolationException ex) {
            LOG.error("Validation error", ex);
            throw new BadRequestException("Validation failed for entity", ex.getMessage(), this.type);
        } catch (EntityAlreadyExistException ex) {
            LOG.error("Entity already exists", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.error("Unexpected error while creating entity", ex);
            throw new BadRequestException("Error creating entity", ex.getMessage(), this.type);
        }
    }

    public T update(String id, T entity) {
        try {
            T existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
            validateEntity(entity);
            entity.setLastModifiedDate(new Date());
            return repository.save(entity);
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found for update", ex);
            throw ex;
        } catch (ConstraintViolationException ex) {
            LOG.error("Validation error during update", ex);
            throw new BadRequestException("Validation failed for entity", ex.getMessage(), type);
        } catch (Exception ex) {
            LOG.error("Unexpected error while updating entity", ex);
            throw new BadRequestException("Error updating entity", ex.getMessage(), type);
        }
    }

    public void deleteAdmin(String id) {
        try {
            T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
            repository.delete(entity);
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found for delete", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.error("Unexpected error while deleting entity administratively", ex);
            throw new BadRequestException("Error deleting entity administratively", ex.getMessage(), type);
        }
    }

    public void delete(String id) {
        try {
            T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
            entity.setActive(false);
            repository.save(entity);
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found for deactivation", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.error("Unexpected error while deactivating entity", ex);
            throw new BadRequestException("Error deactivating entity", ex.getMessage(), type);
        }
    }

    private void validateEntity(T entity) {
       Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
