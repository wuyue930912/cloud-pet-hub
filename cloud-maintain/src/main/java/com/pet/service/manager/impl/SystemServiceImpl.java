package com.pet.service.manager.impl;

import com.pet.constant.LogTypeConstant;
import com.pet.dao.ErrorSysLogDao;
import com.pet.dao.SysLogDao;
import com.pet.enums.ErrorMsgEnum;
import com.pet.service.manager.SystemService;
import com.pet.vo.ResponseResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {

    private final ErrorSysLogDao errorSysLogDao;

    private final SysLogDao sysLogDao;

    @Override
    @Transactional
    public ResponseResultVO<String> clearLog(String type) {
        switch (type) {
            case LogTypeConstant.ALL: {
                errorSysLogDao.clear();
                sysLogDao.clear();
                break;
            }
            case LogTypeConstant.SYS_LOG: {
                sysLogDao.clear();
                break;
            }
            case LogTypeConstant.ERROR_LOG: {
                errorSysLogDao.clear();
                break;
            }
            default:
                break;
        }
        return ResponseResultVO.<String>builder().code(ErrorMsgEnum.SUCCESS.getCode()).msg(ErrorMsgEnum.SUCCESS.getMsg()).build();
    }
}
