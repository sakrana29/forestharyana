package com.haryana.forest.service.impl;

import com.haryana.forest.service.InchargeMasterTypeService;
import com.haryana.forest.domain.InchargeMasterType;
import com.haryana.forest.repository.InchargeMasterTypeRepository;
import com.haryana.forest.service.dto.InchargeMasterTypeDTO;
import com.haryana.forest.service.mapper.InchargeMasterTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing InchargeMasterType.
 */
@Service
public class InchargeMasterTypeServiceImpl implements InchargeMasterTypeService {

    private final Logger log = LoggerFactory.getLogger(InchargeMasterTypeServiceImpl.class);

    private final InchargeMasterTypeRepository inchargeMasterTypeRepository;

    private final InchargeMasterTypeMapper inchargeMasterTypeMapper;

    public InchargeMasterTypeServiceImpl(InchargeMasterTypeRepository inchargeMasterTypeRepository, InchargeMasterTypeMapper inchargeMasterTypeMapper) {
        this.inchargeMasterTypeRepository = inchargeMasterTypeRepository;
        this.inchargeMasterTypeMapper = inchargeMasterTypeMapper;
    }

    /**
     * Save a inchargeMasterType.
     *
     * @param inchargeMasterTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InchargeMasterTypeDTO save(InchargeMasterTypeDTO inchargeMasterTypeDTO) {
        log.debug("Request to save InchargeMasterType : {}", inchargeMasterTypeDTO);

        InchargeMasterType inchargeMasterType = inchargeMasterTypeMapper.toEntity(inchargeMasterTypeDTO);
        inchargeMasterType = inchargeMasterTypeRepository.save(inchargeMasterType);
        return inchargeMasterTypeMapper.toDto(inchargeMasterType);
    }

    /**
     * Get all the inchargeMasterTypes.
     *
     * @return the list of entities
     */
    @Override
    public List<InchargeMasterTypeDTO> findAll() {
        log.debug("Request to get all InchargeMasterTypes");
        return inchargeMasterTypeRepository.findAll().stream()
            .map(inchargeMasterTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one inchargeMasterType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<InchargeMasterTypeDTO> findOne(UUID id) {
        log.debug("Request to get InchargeMasterType : {}", id);
        return inchargeMasterTypeRepository.findById(id)
            .map(inchargeMasterTypeMapper::toDto);
    }

    /**
     * Delete the inchargeMasterType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete InchargeMasterType : {}", id);
        inchargeMasterTypeRepository.deleteById(id);
    }
}
