package com.haryana.forest.service;

import com.haryana.forest.service.dto.RangeMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing RangeMaster.
 */
public interface RangeMasterService {

    /**
     * Save a rangeMaster.
     *
     * @param rangeMasterDTO the entity to save
     * @return the persisted entity
     */
    RangeMasterDTO save(RangeMasterDTO rangeMasterDTO);

    /**
     * Get all the rangeMasters.
     *
     * @return the list of entities
     */
    List<RangeMasterDTO> findAll();


    /**
     * Get the "id" rangeMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RangeMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" rangeMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
