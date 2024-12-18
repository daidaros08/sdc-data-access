package com.sdc.data.repository;

import org.springframework.stereotype.Repository;
import com.sdc.data.model.SampleEntity;

@Repository
public interface SampleRepository extends BaseRepository<SampleEntity, String> {
}
