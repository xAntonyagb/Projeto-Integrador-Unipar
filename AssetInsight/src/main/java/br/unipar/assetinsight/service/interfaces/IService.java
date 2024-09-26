package br.unipar.assetinsight.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<E> {
    E getById(long id);

    Page<E> getAll(Pageable pageable);

    E save(E entity);

    void deleteById(long id);
}
