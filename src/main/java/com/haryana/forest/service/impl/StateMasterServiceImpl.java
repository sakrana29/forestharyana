package com.haryana.forest.service.impl;

import com.haryana.forest.service.StateMasterService;
import com.haryana.forest.domain.StateMaster;
import com.haryana.forest.repository.StateMasterRepository;
import com.haryana.forest.service.dto.StateMasterDTO;
import com.haryana.forest.service.mapper.StateMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing StateMaster.
 */
@Service
public class StateMasterServiceImpl implements StateMasterService {

    private final Logger log = LoggerFactory.getLogger(StateMasterServiceImpl.class);

    private final StateMasterRepository stateMasterRepository;

    private final StateMasterMapper stateMasterMapper;

    public StateMasterServiceImpl(StateMasterRepository stateMasterRepository, StateMasterMapper stateMasterMapper) {
        this.stateMasterRepository = stateMasterRepository;
        this.stateMasterMapper = stateMasterMapper;
    }

    /**
     * Save a stateMaster.
     *
     * @param stateMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StateMasterDTO save(StateMasterDTO stateMasterDTO) {
        log.debug("Request to save StateMaster : {}", stateMasterDTO);

        StateMaster stateMaster = stateMasterMapper.toEntity(stateMasterDTO);
        stateMaster = stateMasterRepository.save(stateMaster);
        return stateMasterMapper.toDto(stateMaster);
    }

    /**
     * Get all the stateMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<StateMasterDTO> findAll() {
        log.debug("Request to get all StateMasters");
        return stateMasterRepository.findAll().stream()
            .map(stateMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one stateMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<StateMasterDTO> findOne(UUID id) {
        log.debug("Request to get StateMaster : {}", id);
        return stateMasterRepository.findById(id)
            .map(stateMasterMapper::toDto);
    }

    /**
     * Delete the stateMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete StateMaster : {}", id);
        stateMasterRepository.deleteById(id);
    }
}
