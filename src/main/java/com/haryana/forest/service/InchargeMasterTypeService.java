package com.haryana.forest.service;

import com.haryana.forest.service.dto.InchargeMasterTypeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing InchargeMasterType.
 */
public interface InchargeMasterTypeService {

    /**
     * Save a inchargeMasterType.
     *
     * @param inchargeMasterTypeDTO the entity to save
     * @return the persisted entity
     */
    InchargeMasterTypeDTO save(InchargeMasterTypeDTO inchargeMasterTypeDTO);

    /**
     * Get all the inchargeMasterTypes.
     *
     * @return the list of entities
     */
    List<InchargeMasterTypeDTO> findAll();


    /**
     * Get the "id" inchargeMasterType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InchargeMasterTypeDTO> findOne(UUID id);

    /**
     * Delete the "id" inchargeMasterType.
     *
     * @param id the id of the entity
     */
    void delete(UUID id);
}
