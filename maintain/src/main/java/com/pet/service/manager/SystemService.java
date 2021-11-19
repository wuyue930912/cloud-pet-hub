package com.pet.service.manager;

import com.pet.vo.ResponseResultVO;

public interface SystemService {

    ResponseResultVO<String> clearLog(String type);
}
