package com.pet.convert;

import com.pet.dto.AddUserDTO;
import com.pet.po.SysUser;
import com.pet.vo.AddUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsersConvert {
    UsersConvert INSTANCE = Mappers.getMapper(UsersConvert.class);

    AddUserDTO vo2dto(AddUserVO vo);

    SysUser dto2po(AddUserDTO dto);
}