package com.haryana.forest.service.impl;

import com.haryana.forest.service.RangeMasterService;
import com.haryana.forest.domain.RangeMaster;
import com.haryana.forest.repository.RangeMasterRepository;
import com.haryana.forest.service.dto.RangeMasterDTO;
import com.haryana.forest.service.mapper.RangeMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RangeMaster.
 */
@Service
public class RangeMasterServiceImpl implements RangeMasterService {

    private final Logger log = LoggerFactory.getLogger(RangeMasterServiceImpl.class);

    private final RangeMasterRepository rangeMasterRepository;

    private final RangeMasterMapper rangeMasterMapper;

    public RangeMasterServiceImpl(RangeMasterRepository rangeMasterRepository, RangeMasterMapper rangeMasterMapper) {
        this.rangeMasterRepository = rangeMasterRepository;
        this.rangeMasterMapper = rangeMasterMapper;
    }

    /**
     * Save a rangeMaster.
     *
     * @param rangeMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RangeMasterDTO save(RangeMasterDTO rangeMasterDTO) {
        log.debug("Request to save RangeMaster : {}", rangeMasterDTO);

        RangeMaster rangeMaster = rangeMasterMapper.toEntity(rangeMasterDTO);
        rangeMaster = rangeMasterRepository.save(rangeMaster);
        return rangeMasterMapper.toDto(rangeMaster);
    }

    /**
     * Get all the rangeMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<RangeMasterDTO> findAll() {
        log.debug("Request to get all RangeMasters");
        return rangeMasterRepository.findAll().stream()
            .map(rangeMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one rangeMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<RangeMasterDTO> findOne(UUID id) {
        log.debug("Request to get RangeMaster : {}", id);
        return rangeMasterRepository.findById(id)
            .map(rangeMasterMapper::toDto);
    }

    /**
     * Delete the rangeMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete RangeMaster : {}", id);
        rangeMasterRepository.deleteById(id);
    }
}
