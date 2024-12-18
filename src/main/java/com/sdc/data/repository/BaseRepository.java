package com.sdc.data.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sdc.data.model.BaseEntity;

public interface BaseRepository <T extends BaseEntity, String> extends MongoRepository<T, String> {

    Page<T> findByActiveTrue(Pageable pageable);

    Page<T> findByCreationDateBetween(Date startDate, Date endDate, Pageable pageable);

    Page<T> findByActive(Boolean active, Pageable pageable);

}
