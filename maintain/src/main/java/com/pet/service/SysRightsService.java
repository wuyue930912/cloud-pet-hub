package com.pet.service;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.dao.SysRightsDao;
import com.pet.dto.SysRightsDTO;
import com.pet.po.SysRights;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysRightsVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SysRightsService {
    private final SysRightsDao rightsDao;

    @Transactional
    public Optional<ResponseResultVO> createRights(SysRightsVo sysRightsVo) {
        String rightsName = sysRightsVo.getRightsName();
        String rightsUrl = sysRightsVo.getRightsUrl();
        String rightsIcon = sysRightsVo.getRightsIcon();

        //重名校验
        Optional<SysRights> optional = rightsDao.findByRightsName(rightsName);
        if (optional.isPresent()) {
            return Optional.of(ResponseResultVO.builder()
                    .data(optional.get().getRightsName())
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.ROLE_ALREADY_EXIST)
                    .build());
        }

        SysRights rights = new SysRights();
        rights.setRightsName(rightsName);
        rights.setRightsUrl(rightsUrl);
        rights.setRightsIcon(rightsIcon);
        rightsDao.save(rights);

        SysRightsDTO sysRightsDTO = new SysRightsDTO();
        sysRightsDTO.setRightsName(rightsName);
        sysRightsDTO.setRightsUrl(rightsUrl);
        sysRightsDTO.setRightsIcon(rightsIcon);

        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(sysRightsDTO)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }


    @Transactional
    public Optional<String> deleteRights(List<String> rightId) {
        try {
            rightsDao.deleteIn(rightId);
            return Optional.of("success");
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Object findRights(String rightId, int pageIndex, int pageSize) {
        SysRightsDTO sysRightsDTO = new SysRightsDTO();

        if(rightId == null || rightId.equals("")){
            sysRightsDTO.setRolesList(rightsDao.findAllRights(pageIndex,pageSize));
        }else {
            SysRights sysRights = rightsDao.findRights(rightId);
            sysRightsDTO.setRightsName(sysRights.getRightsName());
            sysRightsDTO.setRightsUrl(sysRights.getRightsUrl());
            sysRightsDTO.setRightsIcon(sysRights.getRightsIcon());
        }

        return sysRightsDTO;
    }

    public Optional<ResponseResultVO> editsRights(SysRightsVo sysRightsVo) {

        SysRightsDTO rightsDTO = new SysRightsDTO();
        String rightsId = sysRightsVo.getRightsId();
        SysRights rights = rightsDao.findRights(rightsId);
        if (rights == null) {
            return Optional.empty();
        }

        rights.setRightsName(sysRightsVo.getRightsName());
        rights.setRightsUrl(sysRightsVo.getRightsUrl());
        rights.setRightsIcon(sysRightsVo.getRightsIcon());
        rightsDao.save(rights);

        rightsDTO.setRightsName(rights.getRightsName());
        rightsDTO.setRightsUrl(rights.getRightsUrl());
        rightsDTO.setRightsIcon(rights.getRightsIcon());

        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(rightsDTO)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }
}
