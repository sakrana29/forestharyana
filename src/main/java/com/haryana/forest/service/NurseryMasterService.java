package com.haryana.forest.service;

import com.haryana.forest.service.dto.NurseryMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing NurseryMaster.
 */
public interface NurseryMasterService {

    /**
     * Save a nurseryMaster.
     *
     * @param nurseryMasterDTO the entity to save
     * @return the persisted entity
     */
    NurseryMasterDTO save(NurseryMasterDTO nurseryMasterDTO);

    /**
     * Get all the nurseryMasters.
     *
     * @return the list of entities
     */
    List<NurseryMasterDTO> findAll();


    /**
     * Get the "id" nurseryMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NurseryMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" nurseryMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
