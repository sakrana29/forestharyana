package com.haryana.forest.service;

import com.haryana.forest.service.dto.BeatMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing BeatMaster.
 */
public interface BeatMasterService {

    /**
     * Save a beatMaster.
     *
     * @param beatMasterDTO the entity to save
     * @return the persisted entity
     */
    BeatMasterDTO save(BeatMasterDTO beatMasterDTO);

    /**
     * Get all the beatMasters.
     *
     * @return the list of entities
     */
    List<BeatMasterDTO> findAll();


    /**
     * Get the "id" beatMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BeatMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" beatMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
