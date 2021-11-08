package com.pet.convert;

import com.pet.event.entity.LogToDbEventEntity;
import com.pet.po.SysLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SysLogConvert {

    SysLogConvert INSTANCE = Mappers.getMapper(SysLogConvert.class);

    SysLog dto2po(LogToDbEventEntity dto);

}
