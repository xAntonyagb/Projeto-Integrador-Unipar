package br.unipar.assetinsight.repositories.custom.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ICustomRepository<E> {
    Page<E> findAllWithFilters(Pageable pageable, Map<String, String> filtros);
}
