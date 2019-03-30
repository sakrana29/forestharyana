package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.StateMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StateMaster and its DTO StateMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StateMasterMapper extends EntityMapper<StateMasterDTO, StateMaster> {


}
