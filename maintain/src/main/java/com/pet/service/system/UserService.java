package com.pet.service.system;

import com.pet.dto.AddUserDTO;
import com.pet.vo.PageParamVO;
import com.pet.vo.PageResultVO;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.system.SearchUserResultVO;
import com.pet.vo.system.SearchUserVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseResultVO<String> add(AddUserDTO vo2dto);

    ResponseResultVO<String> delUser(String userId);

    ResponseResultVO<PageResultVO<List<SearchUserResultVO>>> searchUser(PageParamVO<SearchUserVO> pageParam);
}
