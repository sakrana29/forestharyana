package com.haryana.forest.service.impl;

import com.haryana.forest.service.NurseryMasterService;
import com.haryana.forest.domain.NurseryMaster;
import com.haryana.forest.repository.NurseryMasterRepository;
import com.haryana.forest.service.dto.NurseryMasterDTO;
import com.haryana.forest.service.mapper.NurseryMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing NurseryMaster.
 */
@Service
public class NurseryMasterServiceImpl implements NurseryMasterService {

    private final Logger log = LoggerFactory.getLogger(NurseryMasterServiceImpl.class);

    private final NurseryMasterRepository nurseryMasterRepository;

    private final NurseryMasterMapper nurseryMasterMapper;

    public NurseryMasterServiceImpl(NurseryMasterRepository nurseryMasterRepository, NurseryMasterMapper nurseryMasterMapper) {
        this.nurseryMasterRepository = nurseryMasterRepository;
        this.nurseryMasterMapper = nurseryMasterMapper;
    }

    /**
     * Save a nurseryMaster.
     *
     * @param nurseryMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NurseryMasterDTO save(NurseryMasterDTO nurseryMasterDTO) {
        log.debug("Request to save NurseryMaster : {}", nurseryMasterDTO);

        NurseryMaster nurseryMaster = nurseryMasterMapper.toEntity(nurseryMasterDTO);
        nurseryMaster = nurseryMasterRepository.save(nurseryMaster);
        return nurseryMasterMapper.toDto(nurseryMaster);
    }

    /**
     * Get all the nurseryMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<NurseryMasterDTO> findAll() {
        log.debug("Request to get all NurseryMasters");
        return nurseryMasterRepository.findAll().stream()
            .map(nurseryMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nurseryMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<NurseryMasterDTO> findOne(UUID id) {
        log.debug("Request to get NurseryMaster : {}", id);
        return nurseryMasterRepository.findById(id)
            .map(nurseryMasterMapper::toDto);
    }

    /**
     * Delete the nurseryMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete NurseryMaster : {}", id);
        nurseryMasterRepository.deleteById(id);
    }
}
