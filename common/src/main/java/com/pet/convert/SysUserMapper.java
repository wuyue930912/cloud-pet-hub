package com.pet.convert;

import com.pet.dto.SysUserDTO;
import com.pet.po.SysUser;
import com.pet.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * dto/vo/po转换接口
 */
@Mapper
public interface SysUserMapper {
    SysUserMapper INSTANCE = Mappers.getMapper(SysUserMapper.class);

    @Mappings({
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "userNickName", target = "userNickName"),
            @Mapping(source = "userIcon", target = "userIcon"),
            @Mapping(source = "userPhone", target = "userPhone"),
            @Mapping(source = "userEmail", target = "userEmail")
    })
    SysUserDTO po2dto (SysUser user);

    @Mappings({
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "userNickName", target = "userNickName"),
            @Mapping(source = "userIcon", target = "userIcon"),
            @Mapping(source = "userPhone", target = "userPhone"),
            @Mapping(source = "userEmail", target = "userEmail")
    })
    SysUserVO dto2vo (SysUserDTO sysUserDTO);
}
