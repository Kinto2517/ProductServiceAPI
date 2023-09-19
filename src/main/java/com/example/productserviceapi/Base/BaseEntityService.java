package com.example.productserviceapi.Base;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E,Long>> {

    private final R repository;

    private static final Logger logger = LogManager.getLogger(BaseEntityService.class);
    public E save(E entity) {

        BaseAdditionalFields additionalFields = entity.getAdditionalFields();
        if (additionalFields == null) {
            additionalFields = new BaseAdditionalFields();
        }

        if (entity.getId() == null) {
            additionalFields.setCreateDate(LocalDateTime.now());
        }

        additionalFields.setUpdateDate(LocalDateTime.now());

        entity.setAdditionalFields(additionalFields);

        entity = repository.save(entity);

        logger.info("Entity saved: " + entity.toString());

        return entity;
    }

    public List<E> findAll() {
        logger.info("Entity findAll: " + repository.findAll().toString());

        return repository.findAll();
    }
    public Optional<E> findById(Long id) {
        logger.info("Entity findById: " + repository.findById(id).toString());

        return repository.findById(id);
    }
    public void delete(Long id) {
        logger.info("Entity delete: " + repository.findById(id).toString());

        repository.deleteById(id);
    }
    public void delete(E entity) {
        logger.info("Entity delete: " + entity.toString());

        repository.delete(entity);
    }

}
