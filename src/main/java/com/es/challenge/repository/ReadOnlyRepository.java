package com.es.challenge.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;


/**
 * @author fjt
 * @date 2023-02-16
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T findOne(ID id);

    List<T> findAll();
}
