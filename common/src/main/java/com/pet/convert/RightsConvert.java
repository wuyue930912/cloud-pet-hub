package com.pet.convert;

import com.pet.dto.SysMapRightsDTO;
import com.pet.po.SysRights;
import com.pet.vo.SysRightsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RightsConvert {
    RightsConvert INSTANCE = Mappers.getMapper(RightsConvert.class);

    @Mappings({
            @Mapping(source = "rightsId", target = "rightsId"),
            @Mapping(source = "rightsName", target = "rightsName"),
            @Mapping(source = "rightsUrl", target = "rightsUrl"),
            @Mapping(source = "rightsIcon", target = "rightsIcon"),
            @Mapping(source = "parentId", target = "parentId")
    })
    SysRights vo2po(SysRightsVO rightsVO);


    SysMapRightsDTO po2dto(SysRights rights);
}
