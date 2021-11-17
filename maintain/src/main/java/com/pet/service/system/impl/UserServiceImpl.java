package com.pet.service.system.impl;

import com.pet.config.ServiceException;
import com.pet.constant.ErrorCodeConstant;
import com.pet.convert.SysUserConvert;
import com.pet.dao.SysUserDao;
import com.pet.dto.AddUserDTO;
import com.pet.enums.ErrorMsgEnum;
import com.pet.po.SysUser;
import com.pet.service.system.UserService;
import com.pet.utils.PasswordUtil;
import com.pet.vo.PageParamVO;
import com.pet.vo.PageResultVO;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.system.SearchUserResultVO;
import com.pet.vo.system.SearchUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SysUserDao sysUsersDao;

    @Override
    @Transactional
    public ResponseResultVO<String> add(AddUserDTO dto) {

        // 用户名重复校验
        if (sysUsersDao.existsByUserName(dto.getUserName())) {
            throw new ServiceException(ErrorMsgEnum.USER_ALREADY_EXIST.getMsg());
        }

        // 转换成po
        SysUser user = SysUserConvert.INSTANCE.dto2po(dto);

        // 密码加密
        user.setUserPwd(PasswordUtil.encodePassword(dto.getUserPwd()));

        // 入库
        sysUsersDao.save(user);

        // 返回结果
        return ResponseResultVO.<String>builder()
                .code(ErrorCodeConstant.SUCCESS).msg(ErrorMsgEnum.SUCCESS.getMsg()).build();
    }

    @Override
    @Transactional
    public ResponseResultVO<String> delUser(String userId) {
        //通过userid判断该用户是否存在
        if (!sysUsersDao.existsById(userId)) {
            throw new ServiceException(ErrorMsgEnum.USER_NOT_EXIST.getMsg());
        }
        sysUsersDao.deleteById(userId);
        return ResponseResultVO.<String>builder().code(ErrorMsgEnum.SUCCESS.getCode()).msg(ErrorMsgEnum.SUCCESS.getMsg()).build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResultVO<PageResultVO<List<SearchUserResultVO>>> searchUser(PageParamVO<SearchUserVO> pageParam) {
        Page<SysUser> page;
        Pageable pageable = PageRequest.of(pageParam.getPageIndex() - 1, pageParam.getPageSize());
        SearchUserVO searchUserVO = pageParam.getSearchData();
        if (Objects.nonNull(searchUserVO)) {
            SysUser user = new SysUser();
            user.setUserName(searchUserVO.getUserName());
            user.setUserPhone(searchUserVO.getPhone());
            user.setCreateTime(null);

            Example<SysUser> example = Example.of(user);
            page = sysUsersDao.findAll(example, pageable);
        } else {
            page = sysUsersDao.findAll(pageable);
        }

        List<SearchUserResultVO> vos = new ArrayList<>();
        page.get().forEach(po -> vos.add(SysUserConvert.INSTANCE.po2vo(po)));

        return ResponseResultVO.<PageResultVO<List<SearchUserResultVO>>>builder()
                .code(ErrorMsgEnum.SUCCESS.getCode())
                .msg(ErrorMsgEnum.SUCCESS.getMsg())
                .data(PageResultVO.<List<SearchUserResultVO>>builder().result(vos).count(page.getTotalElements()).build())
                .build();
    }
}
