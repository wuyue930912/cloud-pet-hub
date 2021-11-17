package com.pet.convert;

import com.pet.dto.AddUserDTO;
import com.pet.po.SysUser;
import com.pet.vo.system.AddUserVO;
import com.pet.vo.system.SearchUserResultVO;
import io.swagger.annotations.ApiModelProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    AddUserDTO vo2dto(AddUserVO vo);

    SysUser dto2po(AddUserDTO dto);


    @Mappings({
            @Mapping(source = "id", target = "userId"),
    })
    SearchUserResultVO po2vo(SysUser sysUser);
}