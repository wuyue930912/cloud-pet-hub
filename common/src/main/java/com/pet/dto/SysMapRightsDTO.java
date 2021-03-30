package com.pet.dto;

import lombok.Data;

import java.util.List;

@Data
public class SysMapRightsDTO {
    private String rightsId;
    private String rightsName;
    private String parentId;
    private List<SysMapRightsDTO> childList;
}
