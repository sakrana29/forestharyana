package com.haryana.forest.service;

import com.haryana.forest.service.dto.StateMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing StateMaster.
 */
public interface StateMasterService {

    /**
     * Save a stateMaster.
     *
     * @param stateMasterDTO the entity to save
     * @return the persisted entity
     */
    StateMasterDTO save(StateMasterDTO stateMasterDTO);

    /**
     * Get all the stateMasters.
     *
     * @return the list of entities
     */
    List<StateMasterDTO> findAll();


    /**
     * Get the "id" stateMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StateMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" stateMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
