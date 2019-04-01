package com.haryana.forest.service;

import com.haryana.forest.service.dto.DivisionMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing DivisionMaster.
 */
public interface DivisionMasterService {

    /**
     * Save a divisionMaster.
     *
     * @param divisionMasterDTO the entity to save
     * @return the persisted entity
     */
    DivisionMasterDTO save(DivisionMasterDTO divisionMasterDTO);

    /**
     * Get all the divisionMasters.
     *
     * @return the list of entities
     */
    List<DivisionMasterDTO> findAll();


    /**
     * Get the "id" divisionMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DivisionMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" divisionMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
