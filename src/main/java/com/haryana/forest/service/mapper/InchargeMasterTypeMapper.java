package com.haryana.forest.service.mapper;

import com.haryana.forest.domain.*;
import com.haryana.forest.service.dto.InchargeMasterTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InchargeMasterType and its DTO InchargeMasterTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InchargeMasterTypeMapper extends EntityMapper<InchargeMasterTypeDTO, InchargeMasterType> {


}
