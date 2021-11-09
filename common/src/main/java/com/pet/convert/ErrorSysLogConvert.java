package com.pet.convert;

import com.pet.event.entity.ErrorLogToDbEventEntity;
import com.pet.po.ErrorSysLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ErrorSysLogConvert {

    ErrorSysLogConvert INSTANCE = Mappers.getMapper(ErrorSysLogConvert.class);

    ErrorSysLog dto2po(ErrorLogToDbEventEntity dto);

}
