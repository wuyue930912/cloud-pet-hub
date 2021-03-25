package com.pet.convert;

import com.pet.dto.SysUsersDTO;
import com.pet.po.SysUsers;
import com.pet.vo.SysUsersVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersConvert {
    UsersConvert INSTANCE = Mappers.getMapper(UsersConvert.class);

    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "userPhone", target = "userPhone"),
            @Mapping(source = "userEmail", target = "userEmail")
    })
    SysUsers vo2po(SysUsersVo usersVo);

    @Mappings({
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "userPhone", target = "userPhone"),
            @Mapping(source = "userEmail", target = "userEmail")
    })
    SysUsersDTO po2dto(SysUsers usersVo);
}