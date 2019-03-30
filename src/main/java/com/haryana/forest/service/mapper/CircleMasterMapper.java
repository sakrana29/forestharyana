package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.CircleMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CircleMaster and its DTO CircleMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CircleMasterMapper extends EntityMapper<CircleMasterDTO, CircleMaster> {


}
