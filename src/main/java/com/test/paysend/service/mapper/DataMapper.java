package com.test.paysend.service.mapper;

/**
 * Class intended to map @param <S>  source object
 * to @param <T> target.
 */
public interface DataMapper<S, T> {
    /**
     * Converts DTO to model type
     */
    T convertDtoToModel(S source);

    /**
     * Converts model instance to DTO type
     */
    S convertModelToDto(T source);
}
