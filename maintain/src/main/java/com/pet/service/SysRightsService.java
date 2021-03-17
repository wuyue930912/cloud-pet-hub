package com.pet.service;

import com.pet.dao.SysRightsDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SysRightsService {
    private final SysRightsDao rightsDao;
}
