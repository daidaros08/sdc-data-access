package com.sdc.data.service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sdc.data.exception.type.BadRequestException;
import com.sdc.data.exception.type.BaseException;
import com.sdc.data.exception.type.EntityAlreadyExistException;
import com.sdc.data.exception.type.EntityNotFoundException;
import com.sdc.data.model.BaseEntity;
import com.sdc.data.repository.BaseRepository;
import com.sdc.data.service.pagination.PaginatedResult;

@Service
public class BaseService <T extends BaseEntity, R extends BaseRepository<T, String>> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);
    private static final String ENTITY_NOT_FOUND = "service.generic.entity.not-found";
    private static final String ENTITIES_NOT_FOUND = "service.generic.entities.not-found";
    private static final String ENTITY_EXIST_EXCEPTION = "service.generic.entity-exist.exception";
    private static Integer OFFSET = 0;
    private static Integer LIMIT = 1000;

    @Autowired
    protected Validator validator;

    protected final R repository;

    protected Class<T> type;

    public BaseService(Class<T> type, R repository) {
        this.repository = repository;
        this.type = type;
    }

    public PaginatedResult getPaginated(Page<T> page) {
        return new PaginatedResult(page);
    }

    public Page<T> getAll(Pageable pageable) {
        try {
            Page<T> entities = repository.findAll(pageable);
            return entities;
        } catch (BaseException ex) {
            LOG.error("Unexpected error while fetching entities", ex.getEntityClass().getSimpleName());
            throw new BadRequestException("Error fetching entities", this.type);
        }
    }

    public T getById(String id) {
        try {
            return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found", ex.getMessage());
            throw new EntityNotFoundException(ENTITY_NOT_FOUND, this.type);
        } catch (BaseException ex) {
            LOG.error("Unexpected error while fetching entity by ID", ex.getEntityClass().getSimpleName());
            throw new BadRequestException("Error fetching entity by ID", this.type);
        }
    }

    public T create(T entity) {
        try {
            validateEntity(entity);
            if (repository.existsById(entity.getId())) {
                throw new EntityAlreadyExistException(ENTITY_EXIST_EXCEPTION, entity.getClass());
            }
            verifyAndValidateEntityDates(entity, false);
            return repository.insert(entity);
        } catch (ConstraintViolationException ex) {
            LOG.error("Validation error", ex.getConstraintViolations());
            throw new ConstraintViolationException(ex.getConstraintViolations());
        } catch (EntityAlreadyExistException ex) {
            LOG.error("Entity already exists", ex.getEntityClass().getSimpleName());
            throw ex;
        } catch (BaseException ex) {
            LOG.error("Unexpected error while creating entity", ex.getEntityClass().getSimpleName());
            throw new BadRequestException("Error creating entity", this.type);
        }
    }

    public T update(String id, T entity) {
        try {
            T existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
            validateEntity(entity);
            verifyAndValidateEntityDates(existingEntity, true);
            entity.setId(id);
            return repository.save(entity);
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found for update", ex.getMessage());
            throw ex;
        } catch (ConstraintViolationException ex) {
            LOG.error("Validation error during update", ex.getConstraintViolations());
            throw new ConstraintViolationException(ex.getConstraintViolations());
        } catch (BaseException ex) {
            LOG.error("Unexpected error while updating entity", ex.getEntityClass().getSimpleName());
            throw new BadRequestException("Error updating entity", type);
        }
    }

    public void deleteAdmin(String id) {
        try {
            T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
            repository.delete(entity);
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found for delete", ex.getMessage());
            throw ex;
        } catch (BaseException ex) {
            LOG.error("Unexpected error while deleting entity administratively", ex.getEntityClass().getSimpleName());
            throw new BadRequestException("Error deleting entity administratively", type);
        }
    }

    public void delete(String id) {
        try {
            T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
            entity.setActive(false);
            repository.save(entity);
        } catch (EntityNotFoundException ex) {
            LOG.error("Entity not found for deactivation", ex.getEntityClass().getSimpleName());
            throw ex;
        } catch (BaseException ex) {
            LOG.error("Unexpected error while deactivating entity",  ex.getEntityClass().getSimpleName());
            throw new BadRequestException("Error deactivating entity", type);
        }
    }

    private void validateEntity(T entity) {
       Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    protected PageRequest getPage(Integer offsetField, Integer limitField, String sortField, String sortDirection) {
        Sort.Direction dir = Optional.ofNullable(sortDirection).isPresent() ? Sort.Direction.valueOf(sortDirection.toUpperCase()) : Sort.Direction.DESC;
        Sort sort = Optional.ofNullable(sortField).isPresent() ? Sort.by(dir, sortField) : Sort.unsorted();
        Integer offset = Optional.ofNullable(offsetField).orElse(OFFSET);
        Integer limit = Optional.ofNullable(limitField).orElse(LIMIT);
        PageRequest pageRequest = PageRequest.of(offset, limit, sort);
        return pageRequest;
    }

    protected String generateID(){
        return new ObjectId().toString();
    }

    private void verifyAndValidateEntityDates(T entity, Boolean update) {
        if (Objects.isNull(entity.getCreatedDate())) {
            entity.setCreationDate(new Date());
        }
        if (update && Objects.isNull(entity.getLastModifiedDate())) {
            entity.setLastModifiedDate(new Date());
        }
    }
}
