package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.DivisionMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DivisionMaster and its DTO DivisionMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DivisionMasterMapper extends EntityMapper<DivisionMasterDTO, DivisionMaster> {


}
