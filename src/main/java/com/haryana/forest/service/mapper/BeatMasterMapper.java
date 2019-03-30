package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.BeatMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BeatMaster and its DTO BeatMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BeatMasterMapper extends EntityMapper<BeatMasterDTO, BeatMaster> {


}
