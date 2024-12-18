package com.sdc.data.service;


import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import com.sdc.data.exception.EntityAlreadyExistException;
import com.sdc.data.exception.EntityNotFoundException;
import com.sdc.data.model.SampleEntity;
import com.sdc.data.repository.SampleRepository;
import static com.sdc.data.model.Builder.ID;
import static com.sdc.data.model.Builder.buildEntity;
import static com.sdc.data.model.Builder.buildEntityUpdate;
import static com.sdc.data.model.Builder.buildPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BaseServiceTest {


    private SampleEntity activeEntity;
    private SampleEntity inactiveEntity;
    
    @Mock
    private SampleRepository repository;

    private SampleBaseService baseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        baseService = new SampleBaseService(repository);
        activeEntity = buildEntity();
    }

    @Test
    public void shouldReturnAllEntities() {
        mockGetAllEntitiesResponse();

        Page<SampleEntity> result = baseService.getAll(PageRequest.of(0, 10));

        assertNotNull(result);
        verify(repository).findByActiveTrue(any(Pageable.class));
        assertEquals(result.getTotalElements(), 1);
    }

    @Test
    public void shouldReturnEntityWhenEntityExists() {
        mockGetByIdResponse();

        SampleEntity result = baseService.getById(ID);

        assertNotNull(result);
        verify(repository).findById(ID);
        assertEquals(ID, result.getId());
        assertEquals(buildEntity().getId(), result.getId());
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        mockGetByIdErrorResponse();

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            baseService.getById(ID);
        });
        assertEquals("Not Found", exception.getErrorCode());
    }

    @Test
    public void shouldThrowEntityAlreadyExistException() {
        mockExistByIdResponse();

        EntityAlreadyExistException exception = assertThrows(EntityAlreadyExistException.class, () -> {
            baseService.create(buildEntity());
        });
        assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), exception.getErrorCode());
    }

    @Test
    public void shouldCreateEntity() {
        mockCrateEntity();

        SampleEntity result = baseService.create(buildEntity());

        assertNotNull(result);
        verify(repository).insert(any(SampleEntity.class));
        assertEquals(ID, result.getId());
        assertEquals(buildEntity().getId(), result.getId());
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdate() {
        mockGetByIdErrorResponse();

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            baseService.update(ID, buildEntityUpdate());
        });
        assertEquals("Not Found", exception.getErrorCode());
    }

    @Test
    public void shouldDeleteAdminEntity() {
        mockGetByIdResponse();

        baseService.deleteAdmin(ID);

        verify(repository).delete(any(SampleEntity.class));
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenDelete() {
        mockGetByIdErrorResponse();

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            baseService.deleteAdmin(ID);
        });
        assertEquals("Not Found", exception.getErrorCode());
    }

    private void mockGetAllEntitiesResponse() {
        when(repository.findByActiveTrue(any(Pageable.class)))
            .thenReturn(buildPage(Arrays.asList(buildEntity())));
    }

    private void mockGetByIdResponse() {
        when(repository.findById(anyString())).thenReturn(Optional.of(buildEntity()));
    }

    private void mockGetByIdErrorResponse() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
    }

    private void mockExistByIdResponse() {
        when(repository.existsById(anyString())).thenReturn(true);
    }

    private void mockCrateEntity() {
        when(repository.insert(any(SampleEntity.class))).thenReturn(buildEntity());
    }
}
