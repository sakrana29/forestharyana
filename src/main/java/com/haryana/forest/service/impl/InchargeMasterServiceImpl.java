package com.haryana.forest.service.impl;

import com.haryana.forest.service.InchargeMasterService;
import com.haryana.forest.domain.InchargeMaster;
import com.haryana.forest.repository.InchargeMasterRepository;
import com.haryana.forest.service.dto.InchargeMasterDTO;
import com.haryana.forest.service.mapper.InchargeMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing InchargeMaster.
 */
@Service
public class InchargeMasterServiceImpl implements InchargeMasterService {

    private final Logger log = LoggerFactory.getLogger(InchargeMasterServiceImpl.class);

    private final InchargeMasterRepository inchargeMasterRepository;

    private final InchargeMasterMapper inchargeMasterMapper;

    public InchargeMasterServiceImpl(InchargeMasterRepository inchargeMasterRepository, InchargeMasterMapper inchargeMasterMapper) {
        this.inchargeMasterRepository = inchargeMasterRepository;
        this.inchargeMasterMapper = inchargeMasterMapper;
    }

    /**
     * Save a inchargeMaster.
     *
     * @param inchargeMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InchargeMasterDTO save(InchargeMasterDTO inchargeMasterDTO) {
        log.debug("Request to save InchargeMaster : {}", inchargeMasterDTO);

        InchargeMaster inchargeMaster = inchargeMasterMapper.toEntity(inchargeMasterDTO);
        inchargeMaster = inchargeMasterRepository.save(inchargeMaster);
        return inchargeMasterMapper.toDto(inchargeMaster);
    }

    /**
     * Get all the inchargeMasters.
     *
     * @return the list of entities
     */
    @Override
    public List<InchargeMasterDTO> findAll() {
        log.debug("Request to get all InchargeMasters");
        return inchargeMasterRepository.findAll().stream()
            .map(inchargeMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one inchargeMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<InchargeMasterDTO> findOne(UUID id) {
        log.debug("Request to get InchargeMaster : {}", id);
        return inchargeMasterRepository.findById(id)
            .map(inchargeMasterMapper::toDto);
    }

    /**
     * Delete the inchargeMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(UUID id) {
        log.debug("Request to delete InchargeMaster : {}", id);
        inchargeMasterRepository.deleteById(id);
    }
}
