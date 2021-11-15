package com.pet.service.system;

import com.pet.dto.AddUserDTO;
import com.pet.vo.ResponseResultVO;

public interface UserService {

    ResponseResultVO<String> add(AddUserDTO vo2dto);

    ResponseResultVO<String> delUser(String userId);
}
