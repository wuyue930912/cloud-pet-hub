package com.pet.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RightsConvert {
    RightsConvert INSTANCE = Mappers.getMapper(RightsConvert.class);

}
