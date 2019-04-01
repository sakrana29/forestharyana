package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.NurseryMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NurseryMaster and its DTO NurseryMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NurseryMasterMapper extends EntityMapper<NurseryMasterDTO, NurseryMaster> {


}
