package com.pet.dto;

import com.pet.po.SysRights;
import lombok.Data;

import java.util.List;

@Data
public class SysRightsDTO {

    private String rightsId;
    private String rightsName;
    private String rightsUrl;
    private String rightsIcon;
    private String parentId;
    private List<SysRights> rightList;
}
