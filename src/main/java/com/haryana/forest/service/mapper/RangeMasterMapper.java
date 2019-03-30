package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.RangeMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RangeMaster and its DTO RangeMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RangeMasterMapper extends EntityMapper<RangeMasterDTO, RangeMaster> {


}
