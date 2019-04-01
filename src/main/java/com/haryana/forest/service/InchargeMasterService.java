package com.haryana.forest.service;

import com.haryana.forest.service.dto.InchargeMasterDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing InchargeMaster.
 */
public interface InchargeMasterService {

    /**
     * Save a inchargeMaster.
     *
     * @param inchargeMasterDTO the entity to save
     * @return the persisted entity
     */
    InchargeMasterDTO save(InchargeMasterDTO inchargeMasterDTO);

    /**
     * Get all the inchargeMasters.
     *
     * @return the list of entities
     */
    List<InchargeMasterDTO> findAll();


    /**
     * Get the "id" inchargeMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InchargeMasterDTO> findOne(UUID id);

    /**
     * Delete the "id" inchargeMaster.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
