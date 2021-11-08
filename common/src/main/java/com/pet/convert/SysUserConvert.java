package com.pet.convert;

import com.pet.dto.AddUserDTO;
import com.pet.po.SysUser;
import com.pet.vo.system.AddUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    AddUserDTO vo2dto(AddUserVO vo);

    SysUser dto2po(AddUserDTO dto);
}