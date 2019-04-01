package com.haryana.forest.service;

import com.haryana.forest.service.dto.CircleMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing CircleMaster.
 */
public interface CircleMasterService {

    /**
     * Save a circleMaster.
     *
     * @param circleMasterDTO the entity to save
     * @return the persisted entity
     */
    CircleMasterDTO save(CircleMasterDTO circleMasterDTO);

    /**
     * Get all the circleMasters.
     *
     * @return the list of entities
     */
    List<CircleMasterDTO> findAll();


    /**
     * Get the "id" circleMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CircleMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" circleMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
