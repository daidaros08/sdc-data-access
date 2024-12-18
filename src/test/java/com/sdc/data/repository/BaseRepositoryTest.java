package com.sdc.data.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.sdc.data.model.SampleEntity;
import static com.sdc.data.model.Builder.NAME;
import static com.sdc.data.model.Builder.buildEntity;
import static com.sdc.data.model.Builder.buildPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseRepositoryTest {

    @Mock
    private SampleRepository baseRepository;

    private SampleEntity activeEntity;
    private SampleEntity inactiveEntity;
    private Date startDate;
    private Date endDate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startDate = new Date();
        endDate = new Date();
        activeEntity = buildEntity();
        inactiveEntity = buildEntity();
        inactiveEntity.setActive(false);
        inactiveEntity.setId("124");
    }

    @Test
    void shouldReturnActiveEntity() {
        mockActiveEntityResponse();

        Page<SampleEntity> result = baseRepository.findByActiveTrue(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals(NAME, result.getContent().get(0).getName());
    }

    @Test
    void shouldReturnByCreationDate() {
        mockSarchDateEntityResponse();

        Page<SampleEntity> result = baseRepository.findByCreationDateBetween(startDate, endDate, PageRequest.of(0, 10));

        assertEquals(2, result.getTotalElements());
        assertEquals(NAME, result.getContent().get(0).getName());
    }

    @Test
    void shouldReturnInactiveEntity() {
        mockInactiveEntityResponse();

        Page<SampleEntity> result = baseRepository.findByActive(false, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals(NAME, result.getContent().get(0).getName());
    }

    private void mockInactiveEntityResponse() {
        when(baseRepository.findByActive(any(Boolean.class), any(Pageable.class)))
            .thenReturn(buildPage(Arrays.asList(inactiveEntity)));
    }

    private void mockSarchDateEntityResponse() {
        when(baseRepository.findByCreationDateBetween(any(Date.class), any(Date.class), any(Pageable.class)))
            .thenReturn(buildPage(Arrays.asList(activeEntity, inactiveEntity)));
    }

    private void mockActiveEntityResponse() {
        when(baseRepository.findByActiveTrue(any(Pageable.class))).thenReturn(buildPage(Arrays.asList(activeEntity)));
    }

}
