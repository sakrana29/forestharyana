package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.InchargeMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InchargeMaster and its DTO InchargeMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InchargeMasterMapper extends EntityMapper<InchargeMasterDTO, InchargeMaster> {


}
