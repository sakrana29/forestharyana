package com.haryana.forest.service.impl;

import com.haryana.forest.service.BeatMasterService;
import com.haryana.forest.domain.BeatMaster;
import com.haryana.forest.repository.BeatMasterRepository;
import com.haryana.forest.service.dto.BeatMasterDTO;
import com.haryana.forest.service.mapper.BeatMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BeatMaster.
 */
@Service
public class BeatMasterServiceImpl implements BeatMasterService {

    private final Logger log = LoggerFactory.getLogger(BeatMasterServiceImpl.class);

    private final BeatMasterRepository beatMasterRepository;

    private final BeatMasterMapper beatMasterMapper;

    public BeatMasterServiceImpl(BeatMasterRepository beatMasterRepository, BeatMasterMapper beatMasterMapper) {
        this.beatMasterRepository = beatMasterRepository;
        this.beatMasterMapper = beatMasterMapper;
    }

    /**
     * Save a beatMaster.
     *
     * @param beatMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BeatMasterDTO save(BeatMasterDTO beatMasterDTO) {
        log.debug("Request to save BeatMaster : {}", beatMasterDTO);

        BeatMaster beatMaster = beatMasterMapper.toEntity(beatMasterDTO);
        beatMaster = beatMasterRepository.save(beatMaster);
        return beatMasterMapper.toDto(beatMaster);
    }

    /**
     * Get all the beatMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<BeatMasterDTO> findAll() {
        log.debug("Request to get all BeatMasters");
        return beatMasterRepository.findAll().stream()
            .map(beatMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one beatMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<BeatMasterDTO> findOne(UUID id) {
        log.debug("Request to get BeatMaster : {}", id);
        return beatMasterRepository.findById(id)
            .map(beatMasterMapper::toDto);
    }

    /**
     * Delete the beatMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete BeatMaster : {}", id);
        beatMasterRepository.deleteById(id);
    }
}
