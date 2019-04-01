package com.haryana.forest.service.impl;

import com.haryana.forest.service.DivisionMasterService;
import com.haryana.forest.domain.DivisionMaster;
import com.haryana.forest.repository.DivisionMasterRepository;
import com.haryana.forest.service.dto.DivisionMasterDTO;
import com.haryana.forest.service.mapper.DivisionMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DivisionMaster.
 */
@Service
public class DivisionMasterServiceImpl implements DivisionMasterService {

    private final Logger log = LoggerFactory.getLogger(DivisionMasterServiceImpl.class);

    private final DivisionMasterRepository divisionMasterRepository;

    private final DivisionMasterMapper divisionMasterMapper;

    public DivisionMasterServiceImpl(DivisionMasterRepository divisionMasterRepository, DivisionMasterMapper divisionMasterMapper) {
        this.divisionMasterRepository = divisionMasterRepository;
        this.divisionMasterMapper = divisionMasterMapper;
    }

    /**
     * Save a divisionMaster.
     *
     * @param divisionMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DivisionMasterDTO save(DivisionMasterDTO divisionMasterDTO) {
        log.debug("Request to save DivisionMaster : {}", divisionMasterDTO);

        DivisionMaster divisionMaster = divisionMasterMapper.toEntity(divisionMasterDTO);
        divisionMaster = divisionMasterRepository.save(divisionMaster);
        return divisionMasterMapper.toDto(divisionMaster);
    }

    /**
     * Get all the divisionMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<DivisionMasterDTO> findAll() {
        log.debug("Request to get all DivisionMasters");
        return divisionMasterRepository.findAll().stream()
            .map(divisionMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one divisionMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<DivisionMasterDTO> findOne(UUID id) {
        log.debug("Request to get DivisionMaster : {}", id);
        return divisionMasterRepository.findById(id)
            .map(divisionMasterMapper::toDto);
    }

    /**
     * Delete the divisionMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete DivisionMaster : {}", id);
        divisionMasterRepository.deleteById(id);
    }
}
