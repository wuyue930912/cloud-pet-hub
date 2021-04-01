package com.pet.service;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.convert.RightsConvert;
import com.pet.dao.SysRightsDao;
import com.pet.dao.SysRoleRightsDao;
import com.pet.dto.SysMapRightsDTO;
import com.pet.dto.SysRightsDTO;
import com.pet.po.SysRights;
import com.pet.po.SysRoleRights;
import com.pet.utils.ControllerUtil;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysRightsVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysRightsService {
    private final SysRightsDao rightsDao;
    private final SysRoleRightsDao roleRightsDao;
    @Transactional
    public Optional<ResponseResultVO> createRights(SysRightsVO sysRightsVo) {
        String rightsName = sysRightsVo.getRightsName();
        //重名校验
        Optional<SysRights> optional = rightsDao.findByRightsName(rightsName);
        if (optional.isPresent()) {
            return Optional.of(ResponseResultVO.builder()
                    .data(optional.get().getRightsName())
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.ROLE_ALREADY_EXIST)
                    .build());
        }
        SysRights saveRights =rightsDao.save(RightsConvert.INSTANCE.vo2po(sysRightsVo));
        // 构造结果集
        return Optional.of(ControllerUtil.getSuccessResultVO(RightsConvert.INSTANCE.po2dto(saveRights)));
    }

    @Transactional
    public Optional<String> deleteRights(List<String> rightId) {
        try {
            List<SysRoleRights> roleRightsList = new ArrayList<>();
            for (String rId : rightId) {
                roleRightsList = roleRightsDao.findRightIds(rId);
            }

            if (roleRightsList.size() > 0) {
                return Optional.of("权限被占用,无法删除");
            } else {
                rightsDao.deleteIn(rightId);
            }

            return Optional.of("success");
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<ResponseResultVO> findRights() {

        List<SysRights> rights = rightsDao.findAllRights();
        List<SysMapRightsDTO> dtoList = new ArrayList<>();
        rights.forEach(po -> dtoList.add(RightsConvert.INSTANCE.po2dto(po)));

        Map<String, List<SysMapRightsDTO>> pidListMap = dtoList.stream().collect(Collectors.groupingBy(SysMapRightsDTO::getParentId));
        dtoList.forEach(dto -> dto.setChildList(pidListMap.get(dto.getRightsId())));

        return Optional.of(ControllerUtil.getSuccessResultVO(pidListMap.get("boot")));

    }

    public Optional<ResponseResultVO> editsRights(SysRightsVO sysRightsVo) {

        String rightsId = sysRightsVo.getRightsId();
        SysRights rights = rightsDao.findRights(rightsId);
        if (rights == null) {
            return Optional.empty();
        }
        SysRights editRight = rightsDao.save(RightsConvert.INSTANCE.vo2po(sysRightsVo));
        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(RightsConvert.INSTANCE.po2dto(editRight))
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }

    public Optional<ResponseResultVO> findRightsByRightId(String rightId) {
        SysRightsDTO sysRightsDTO = new SysRightsDTO();
        if (rightId == null || rightId.equals("")) {
            return Optional.empty();
        }
        sysRightsDTO.setRightList(rightsDao.findAllRights());
        return Optional.of(ResponseResultVO.builder()
                .data(sysRightsDTO)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }
}
