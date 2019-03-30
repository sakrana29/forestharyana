package com.haryana.forest.service.impl;

import com.haryana.forest.service.CircleMasterService;
import com.haryana.forest.domain.CircleMaster;
import com.haryana.forest.repository.CircleMasterRepository;
import com.haryana.forest.service.dto.CircleMasterDTO;
import com.haryana.forest.service.mapper.CircleMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CircleMaster.
 */
@Service
public class CircleMasterServiceImpl implements CircleMasterService {

    private final Logger log = LoggerFactory.getLogger(CircleMasterServiceImpl.class);

    private final CircleMasterRepository circleMasterRepository;

    private final CircleMasterMapper circleMasterMapper;

    public CircleMasterServiceImpl(CircleMasterRepository circleMasterRepository, CircleMasterMapper circleMasterMapper) {
        this.circleMasterRepository = circleMasterRepository;
        this.circleMasterMapper = circleMasterMapper;
    }

    /**
     * Save a circleMaster.
     *
     * @param circleMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CircleMasterDTO save(CircleMasterDTO circleMasterDTO) {
        log.debug("Request to save CircleMaster : {}", circleMasterDTO);

        CircleMaster circleMaster = circleMasterMapper.toEntity(circleMasterDTO);
        circleMaster = circleMasterRepository.save(circleMaster);
        return circleMasterMapper.toDto(circleMaster);
    }

    /**
     * Get all the circleMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<CircleMasterDTO> findAll() {
        log.debug("Request to get all CircleMasters");
        return circleMasterRepository.findAll().stream()
            .map(circleMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one circleMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<CircleMasterDTO> findOne(UUID id) {
        log.debug("Request to get CircleMaster : {}", id);
        return circleMasterRepository.findById(id)
            .map(circleMasterMapper::toDto);
    }

    /**
     * Delete the circleMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete CircleMaster : {}", id);
        circleMasterRepository.deleteById(id);
    }
}
