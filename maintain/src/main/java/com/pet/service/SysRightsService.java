package com.pet.service;

import com.pet.dao.SysRightsDao;
import com.pet.dto.SysRightsDTO;
import com.pet.dto.SysRolesDTO;
import com.pet.po.SysRights;
import com.pet.po.SysRoles;
import com.pet.vo.SysRightsVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SysRightsService {
    private final SysRightsDao rightsDao;

    @Transactional
    public Optional<SysRightsDTO> createRights(SysRightsVo sysRightsVo) {
        String rightsName = sysRightsVo.getRightsName();
        String rightsUrl = sysRightsVo.getRightsUrl();
        String rightsIcon = sysRightsVo.getRightsIcon();

        SysRights rights = new SysRights();
        rights.setRightsName(rightsName);
        rights.setRightsUrl(rightsUrl);
        rights.setRightsIcon(rightsIcon);
        rightsDao.save(rights);

        SysRightsDTO sysRightsDTO = new SysRightsDTO();
        sysRightsDTO.setRightsName(rightsName);
        sysRightsDTO.setRightsUrl(rightsUrl);
        sysRightsDTO.setRightsIcon(rightsIcon);

        return Optional.of(sysRightsDTO);
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

    public Object findRights(List<String> rightId) {
        SysRightsDTO sysRightsDTO = new SysRightsDTO();
        List<SysRights> rightsList = new ArrayList<>();
        for (String rId : rightId) {
            SysRights sysRights = rightsDao.findRights(rId);
            rightsList.add(sysRights);
        }
        sysRightsDTO.setRolesList(rightsList);
        return sysRightsDTO;
    }

    public Object editsRights(SysRightsVo sysRightsVo) {
        return null;
    }
}
