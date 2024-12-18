package com.sdc.data.model.mapper;
import java.util.List;

import org.mapstruct.Mapper;

/**
 * Base interface for mappers
 *
 * @param <E> entity
 * @param <D> dto
 *
 */
@Mapper(componentModel = "spring")
public interface BaseMapper<E, D> {

    D mapToDto(E entity);

    E mapToEntity(D dto);

    List<D> mapToDto(List<E> entities);

    List<E> mapToEntity(List<D> dtos);
}
